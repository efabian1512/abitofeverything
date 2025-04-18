package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.util.List;
import java.util.UUID;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItemDTO;


public class ShoppingCartDTO {
	
	private UUID id;
	private Double dateCreated;
	private List<ShoppingCartItemDTO> items;
	
	public ShoppingCartDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<ShoppingCartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ShoppingCartDTO [id=" + id + ", dateCreated=" + dateCreated + ", items=" + items + "]";
	}
}
