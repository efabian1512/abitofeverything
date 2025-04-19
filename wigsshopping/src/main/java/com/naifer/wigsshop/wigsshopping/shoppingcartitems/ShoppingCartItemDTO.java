package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.Arrays;
import java.util.UUID;

public class ShoppingCartItemDTO {
	
	private UUID id;
	private String title;
	private byte[] productImage;
	private UUID productId;
	private double price;
	private Integer quantity;
	private String imageType;
	
	public ShoppingCartItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartItemDTO(UUID id, String title, byte[] productImage, UUID productId, double price,
			Integer quantity, String imageType) {
		super();
		this.id = id;
		this.title = title;
		this.productImage = productImage;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
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

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemDTO [id=" + id + ", title=" + title + ", productImage=" + Arrays.toString(productImage)
				+ ", productId=" + productId + ", price=" + price + ", quantity=" + quantity + ", imageType="
				+ imageType + "]";
	}
}
