package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.util.List;
import java.util.UUID;

import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="Shopping_Carts")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	private UUID id;
	
	@Column(name="date_created")
	private Double dateCreated;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_cart_id", referencedColumnName = "id" )
	private List<ShoppingCartItem> items;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(UUID id, Double dateCreated, List<ShoppingCartItem> items) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.items = items;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Double getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Double dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", dateCreated=" + dateCreated + ", items=" + items + "]";
	}
	
}
