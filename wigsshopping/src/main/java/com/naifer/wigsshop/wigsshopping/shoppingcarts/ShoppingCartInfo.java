package com.naifer.wigsshop.wigsshopping.shoppingcarts;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;



public class ShoppingCartInfo {

	private Double dateCreated;
	private String cartId;
	private ShoppingCartItem item;
	
	public ShoppingCartInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartInfo(Double dateCreated, String cartId, ShoppingCartItem item) {
		super();
		this.dateCreated = dateCreated;
		this.cartId = cartId;
		this.item = item;
	}

	public Double getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Double dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public ShoppingCartItem getItem() {
		return item;
	}

	public void setItem(ShoppingCartItem item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "ShoppingCartInfo [dateCreated=" + dateCreated + ", cartId=" + cartId + ", item=" + item + "]";
	}
}
