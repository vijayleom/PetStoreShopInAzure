package com.postgres.jdbc.data.pets.model;

import java.sql.Array;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pets {
	long id;
	String name;
	long category;
	String photourl;
	String status;
	Array tags;
}
