package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingItemService;

import jakarta.validation.Valid;

@RestController
public class ShoppingCartResource {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private ShoppingItemService shoppingItemService;
	
	@GetMapping("shop/shoppingcarts")
	public List<ShoppingCart> getCarts(){
		return shoppingCartService.getCarts();
	}
	
	@GetMapping("shop/shoppingcarts/{id}")
	public EntityModel<ShoppingCart> getCart(@PathVariable UUID id){
		ShoppingCart cart = shoppingCartService.getCartById(id);
		
		EntityModel<ShoppingCart> entityModel = EntityModel.of(cart);
		return entityModel;
	}

	@PostMapping("shop/shoppingcarts/create")
		public String createCart(@Valid @RequestParam("dateCreated") String date){
	
		Double numericDate = Double.parseDouble(date);
		return	shoppingCartService.createCart(numericDate).toString();
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("shop/shoppingcarts/update")
	public EntityModel<ShoppingCart> updateCart(@Valid @RequestParam("dateCreated") String date,  @Valid @RequestParam("items") String items, @Valid @RequestParam("id") String id){
		
		ObjectMapper objectmapper = new ObjectMapper();
	
		List<ShoppingCartItem> actualItems = List.of();
		List<ShoppingCartItem> itemsTosave = List.of();
		
		
	
		try {
			actualItems = objectmapper.readValue(items, List.class);
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		}
	
		ShoppingCart cart = new ShoppingCart();
		Double numericDate = Double.parseDouble(date);
		UUID actualId = UUID.fromString(id);
	
		actualItems.forEach(item -> 
		{
			itemsTosave.add(shoppingItemService.SaveItem(item));
		});
	
		System.out.println(itemsTosave);
		cart.setId(actualId);
		cart.setItems(itemsTosave);
		cart.setDateCreated(numericDate);
		ShoppingCart updatedCart =	shoppingCartService.updateCart(cart);
		EntityModel<ShoppingCart> entityModel = EntityModel.of(updatedCart);
		return entityModel;
	}
}
