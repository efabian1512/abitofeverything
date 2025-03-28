package com.naifer.wigsshop.wigsshopping.products;

import java.util.UUID;

import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="Shopping_Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String title;
	private double price;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="fk_id")
	private ProductCategory category;

	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "image_type")
	private String imageType;
	
	public Product() {
		super();
	}
	
	public Product(UUID id, String title, double price, ProductCategory category, String imagePath, String imageType) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imagePath = imagePath;
		this.imageType = imageType;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category + ", imagePath="
				+ imagePath + ", imageType=" + imageType + "]";
	}
	
}
