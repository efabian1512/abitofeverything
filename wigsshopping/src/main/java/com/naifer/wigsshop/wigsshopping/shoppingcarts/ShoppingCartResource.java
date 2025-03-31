package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingItemService;

import jakarta.validation.Valid;

@RestController
public class ShoppingCartResource {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
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
	

	@PutMapping("shop/shoppingcarts/addToCart")
	//public EntityModel<ShoppingCart> updateCart(@Valid @RequestParam("dateCreated") String date,  @Valid @RequestParam("items") String items, @Valid @RequestParam("id") String id){
	public EntityModel<ShoppingCart> updateCart(@Valid @RequestParam("dateCreated") String date, @Valid @RequestParam("id") String id, @Valid @RequestParam("item") String item){

        List<ShoppingCartItem> itemsTosave = new ArrayList<ShoppingCartItem>();
			
		Gson gson = new Gson();
	   // Type listType = new ShoppingCartItem() {}.getType();

    	ShoppingCartItem actualItem = gson.fromJson(item, ShoppingCartItem.class);

		ShoppingCart cart = new ShoppingCart();
		Double numericDate = Double.parseDouble(date);
		UUID actualId = UUID.fromString(id);
		itemsTosave.add(actualItem);
		
		cart.setId(actualId);
		//cart.setItems(itemsTosave);
		cart.setDateCreated(numericDate);
		ShoppingCart updatedCart =	shoppingCartService.addToCart(cart, actualItem);
		EntityModel<ShoppingCart> entityModel = EntityModel.of(updatedCart);
		return entityModel;
	}
}
