package com.postgres.jdbc.data.pets;

import java.util.List;

import com.postgres.jdbc.data.pets.model.Category;
import com.postgres.jdbc.data.pets.model.Pets;
import com.postgres.jdbc.data.pets.model.Tags;

public interface PetsRepository {
	
	List<Tags> getTags();
	
	List<Category> getCategory();
	
	List<Pets> getPets();
}
