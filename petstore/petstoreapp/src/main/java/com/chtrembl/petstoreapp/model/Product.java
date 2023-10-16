package com.chtrembl.petstoreapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils;

@SuppressWarnings("serial")
@Component
public class Product implements Serializable {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("name")
	private String name;

	@JsonProperty("photoURL")
	private String photoURL;

	@JsonProperty("tags")
	private List<Tag> tags = null;

	@JsonProperty("quantity")
	private Integer quantity = null;

	public Product id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product category(Category category) {
		this.category = category;
		return this;
	}

	/**
	 * Get category
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get photoUrls
	 * 
	 * @return photoUrls
	 */
	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public Product tags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public Product addTagsItem(Tag tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	/**
	 * Get tags
	 * 
	 * @return tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.category, product.category)
				&& Objects.equals(this.name, product.name) && Objects.equals(this.photoURL, product.photoURL)
				&& Objects.equals(this.tags, product.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, name, photoURL, tags);
	}

	@Override
	public String toString() {
		JSONObject jobj = new JSONObject();
		
		jobj.put("id", id);
		jobj.put("category", category);
		jobj.put("name", name);
		jobj.put("photoUrls", photoURL);
		jobj.put("tags", tags);
		
		
		return jobj.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
