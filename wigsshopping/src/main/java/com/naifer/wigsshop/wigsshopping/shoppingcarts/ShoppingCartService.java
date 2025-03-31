package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;

@Component
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public List<ShoppingCart> getCarts(){
		List<ShoppingCart> carts = shoppingCartRepository.findAll();
		
		if(carts.isEmpty())
			return List.of();
		
		return carts;
	}
	
	public ShoppingCart getCartById(UUID id) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(id);
		
		if(cart.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ id);
		
		return cart.get();
	}
	
	public UUID createCart(Double dateCreated) {
		
		ShoppingCart cart = new ShoppingCart();
		cart.setDateCreated(dateCreated);
		ShoppingCart savedCart = shoppingCartRepository.save(cart);
		return savedCart.getId();
	}
	
	public ShoppingCart addToCart(ShoppingCart cart, ShoppingCartItem item) {
		
		Optional<ShoppingCart> savedCart = shoppingCartRepository.findById(cart.getId());
		if(savedCart.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ cart.getId());
		
		List<ShoppingCartItem> cartItems = new ArrayList<ShoppingCartItem>();
		
		cartItems =	savedCart.get().getItems();
		cartItems.add(item);
		cart.setItems(cartItems);
		
		return shoppingCartRepository.save(cart);
	}
	
}
