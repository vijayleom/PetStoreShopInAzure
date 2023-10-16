package com.postgres.jdbc.data.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.postgres.jdbc.data.products.model.Category;
import com.postgres.jdbc.data.products.model.Products;
import com.postgres.jdbc.data.products.model.Tags;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcProductsRepository implements ProductsRepository {

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
		return jdbcTemplate.query("SELECT id, name from category where categorytype = 'product'", BeanPropertyRowMapper.newInstance(Category.class));
	}

	@Override
	public List<Products> getProducts() {
		return jdbcTemplate.query("SELECT * from products", BeanPropertyRowMapper.newInstance(Products.class));
	}	
}
