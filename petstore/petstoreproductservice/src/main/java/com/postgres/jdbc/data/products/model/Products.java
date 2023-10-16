package com.postgres.jdbc.data.products.model;

import java.sql.Array;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Products {
	long id;
	String name;
	long category;
	String photourl;
	String status;
	Array tags;
}
