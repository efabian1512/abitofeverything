package com.naifer.wigsshop.wigsshopping.productcategories;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Productcategories")
public class ProductCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name="category_name")
	private String categoryName;
	
	public ProductCategory() {
		super();
	}
	
	public ProductCategory(UUID id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", categoryName=" + categoryName + "]";
	}
	
}
