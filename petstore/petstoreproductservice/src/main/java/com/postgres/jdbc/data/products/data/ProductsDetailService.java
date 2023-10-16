package com.postgres.jdbc.data.products.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chtrembl.petstore.product.model.Product;
import com.chtrembl.petstore.product.model.Product.StatusEnum;
import com.chtrembl.petstore.product.model.Tag;
import com.postgres.jdbc.data.products.ProductsRepository;
import com.postgres.jdbc.data.products.model.Category;
import com.postgres.jdbc.data.products.model.Products;
import com.postgres.jdbc.data.products.model.Tags;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductsDetailService {

	@Autowired
	ProductsRepository productsRepository;

	private List<Tags> allTags;

	private List<Category> allCategory;

	public List<Product> getAllProducts() {

		final List<Product> allProducts = new ArrayList<Product>();
		List<Products> allProductsFromDB = null;

		try {

			allTags = productsRepository.getTags();
			log.info("All Tags from Database: {}", allTags);
			allCategory = productsRepository.getCategory();
			log.info("All Categoies from Database: {}", allCategory);
			allProductsFromDB = productsRepository.getProducts();
			log.info("Final Products to be supplied: {}", allProductsFromDB);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while trying to get products from the Databases", e);
		}

		if (allProductsFromDB != null) {
			allProductsFromDB.forEach(product -> {
				allProducts.add(reMapProducts(product));
			});
		}

		return allProducts;
	}

	private Product reMapProducts(Products products) {
		Product product = new Product();
		log.info("Final Products: {0}", products);

		Optional<Category> category = allCategory.stream().filter(cat -> cat.getId() == products.getCategory())
				.findAny();
		//List<Long> tagsList = Arrays.stream(products.getTags()).boxed().collect(Collectors.toList());
		Integer[] javaArray = null;
		try {
			javaArray = (Integer[]) products.getTags().getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Long> tagsList = Arrays.stream(javaArray).map(s-> Long.valueOf(s)).collect(Collectors.toList());
		List<Tags> tags = allTags.stream().filter(tag -> tagsList.contains(tag.getId())).collect(Collectors.toList());

		product.setId(products.getId());
		product.setName(products.getName());
		product.setPhotoURL(products.getPhotourl());
		product.setStatus(StatusEnum.fromValue(products.getStatus()));

		com.chtrembl.petstore.product.model.Category cat = new com.chtrembl.petstore.product.model.Category();
		cat.setId(category.get().getId());
		cat.setName(category.get().getName());
		product.setCategory(cat);

		final List<Tag> allTagsValue = new ArrayList<>();
		tags.forEach(tag -> {
			Tag tagMain = new Tag();
			tagMain.setId(tag.getId());
			tagMain.setName(tag.getName());
			allTagsValue.add(tagMain);
		});
		product.setTags(allTagsValue);
		log.info("Final Product: {}", product);

		return product;
	}
}
