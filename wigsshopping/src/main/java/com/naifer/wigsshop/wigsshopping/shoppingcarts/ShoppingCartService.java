package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

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
	
	public ShoppingCart updateCart(ShoppingCart cart) {
		Optional<ShoppingCart> savedCategory = shoppingCartRepository.findById(cart.getId());
		if(savedCategory.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ cart.getId());
		
		return shoppingCartRepository.save(cart);
	}
	
}
