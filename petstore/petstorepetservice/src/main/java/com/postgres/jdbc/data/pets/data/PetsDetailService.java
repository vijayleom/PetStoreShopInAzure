package com.postgres.jdbc.data.pets.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chtrembl.petstore.pet.model.Pet;
import com.chtrembl.petstore.pet.model.Tag;
import com.chtrembl.petstore.pet.model.Pet.StatusEnum;
import com.postgres.jdbc.data.pets.PetsRepository;
import com.postgres.jdbc.data.pets.model.Category;
import com.postgres.jdbc.data.pets.model.Pets;
import com.postgres.jdbc.data.pets.model.Tags;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetsDetailService {

	@Autowired
	PetsRepository productsRepository;

	private List<Tags> allTags;

	private List<Category> allCategory;

	public List<Pet> getAllPets() {

		final List<Pet> allProducts = new ArrayList<Pet>();
		List<Pets> allPetsFromDB = null;

		try {

			allTags = productsRepository.getTags();
			log.info("All Tags from Database: {}", allTags);
			allCategory = productsRepository.getCategory();
			log.info("All Categoies from Database: {}", allCategory);
			allPetsFromDB = productsRepository.getPets();
			log.info("Final Pets to be supplied: {}", allPetsFromDB);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while trying to get pets from the Databases", e);
		}

		if (allPetsFromDB != null) {
			allPetsFromDB.forEach(pet -> {
				allProducts.add(reMapPets(pet));
			});
		}

		return allProducts;
	}

	private Pet reMapPets(Pets pets) {
		Pet pet = new Pet();
		log.info("Actual DB pet: {}", pets);

		Optional<Category> category = allCategory.stream().filter(cat -> cat.getId() == pets.getCategory())
				.findAny();
		//List<Long> tagsList = Arrays.stream(products.getTags()).boxed().collect(Collectors.toList());
		Integer[] javaArray = null;
		try {
			javaArray = (Integer[]) pets.getTags().getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Long> tagsList = Arrays.stream(javaArray).map(s-> Long.valueOf(s)).collect(Collectors.toList());
		List<Tags> tags = allTags.stream().filter(tag -> tagsList.contains(tag.getId())).collect(Collectors.toList());

		pet.setId(pets.getId());
		pet.setName(pets.getName());
		pet.setPhotoURL(pets.getPhotourl());
		pet.setStatus(StatusEnum.fromValue(pets.getStatus()));

		com.chtrembl.petstore.pet.model.Category cat = new com.chtrembl.petstore.pet.model.Category();
		cat.setId(category.get().getId());
		cat.setName(category.get().getName());
		pet.setCategory(cat);

		final List<Tag> allTagsValue = new ArrayList<>();
		tags.forEach(tag -> {
			Tag tagMain = new Tag();
			tagMain.setId(tag.getId());
			tagMain.setName(tag.getName());
			allTagsValue.add(tagMain);
		});
		pet.setTags(allTagsValue);
		log.info("Final pet: {}", pet);

		return pet;
	}
}
