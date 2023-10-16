package com.postgres.jdbc.data.pets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.postgres.jdbc.data.pets.model.Category;
import com.postgres.jdbc.data.pets.model.Pets;
import com.postgres.jdbc.data.pets.model.Tags;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JdbcPetsRepository implements PetsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Tags> getTags() {
		log.info("Printing the JDBC: {}", String.valueOf(jdbcTemplate));
		return jdbcTemplate.query("SELECT * from tags", BeanPropertyRowMapper.newInstance(Tags.class));
	}

	@Override
	public List<Category> getCategory() {
		log.info("Printing the JDBC: {}", String.valueOf(jdbcTemplate));
		return jdbcTemplate.query("SELECT id, name from category where categorytype = 'pet'", BeanPropertyRowMapper.newInstance(Category.class));
	}

	@Override
	public List<Pets> getPets() {
		return jdbcTemplate.query("SELECT * from pets", BeanPropertyRowMapper.newInstance(Pets.class));
	}	
}
