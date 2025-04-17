package com.naifer.wigsshop.wigsshopping.products;

import java.util.Arrays;
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
import jakarta.persistence.Transient;

@Entity(name="Shopping_Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String title;
	private double price;
	@ManyToOne()
	@JoinColumn(name="fk_id")
	private ProductCategory category;

	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "image_type")
	private String imageType;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Transient 
	private byte[] productImage;
	
	public Product() {
		super();
	}

	public Product(UUID id, String title, double price, ProductCategory category, String imagePath, String imageType,
			String imageName, byte[] productImage) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imagePath = imagePath;
		this.imageType = imageType;
		this.imageName = imageName;
		this.productImage = productImage;
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
		return price;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category + ", imagePath="
				+ imagePath + ", imageType=" + imageType + ", imageName=" + imageName + ", productImage="
				+ Arrays.toString(productImage) + "]";
	}
}
