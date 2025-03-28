package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.UUID;

import com.naifer.wigsshop.wigsshopping.products.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="Shopping_Cart_Items")
public class ShoppingCartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="fk_id")
	private Product product;
	private Integer quantity;
	
	public ShoppingCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartItem(UUID id, Product product, Integer quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}

	
}
