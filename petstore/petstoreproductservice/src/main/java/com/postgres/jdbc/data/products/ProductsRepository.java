package com.postgres.jdbc.data.products;

import java.util.List;

import com.postgres.jdbc.data.products.model.Category;
import com.postgres.jdbc.data.products.model.Products;
import com.postgres.jdbc.data.products.model.Tags;

public interface ProductsRepository {
	
	List<Tags> getTags();
	
	List<Category> getCategory();
	
	List<Products> getProducts();
}
