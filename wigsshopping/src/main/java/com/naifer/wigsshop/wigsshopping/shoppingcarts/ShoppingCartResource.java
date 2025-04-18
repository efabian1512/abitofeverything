package com.naifer.wigsshop.wigsshopping.shoppingcarts;

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
import com.naifer.wigsshop.wigsshopping.products.Product;
import com.naifer.wigsshop.wigsshopping.products.ProductService;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;

import jakarta.validation.Valid;

@RestController
public class ShoppingCartResource {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("shop/shoppingcarts")
	public List<ShoppingCart> getCarts(){
		return shoppingCartService.getCarts();
	}
	
	@GetMapping("shop/shoppingcarts/{id}")
	public EntityModel<ShoppingCartDTO> getCart(@PathVariable UUID id){
		ShoppingCartDTO cart = shoppingCartService.getCartById(id);
		
		EntityModel<ShoppingCartDTO> entityModel = EntityModel.of(cart);
		return entityModel;
	}

	@PostMapping("shop/shoppingcarts/create")
		public String createCart(@Valid @RequestParam("dateCreated") String date){
	
		Double numericDate = Double.parseDouble(date);
		return	shoppingCartService.createCart(numericDate).toString();
	}
	

	@PutMapping("shop/shoppingcarts/addToCart")
	//public EntityModel<ShoppingCart> updateCart(@Valid @RequestParam("dateCreated") String date,  @Valid @RequestParam("items") String items, @Valid @RequestParam("id") String id){
	//public EntityModel<ShoppingCart> updateCart(@Valid @RequestParam("dateCreated") String date, @Valid @RequestParam("id") String id, @Valid @RequestParam("item") String item){
	public EntityModel<ShoppingCart> updateCart(@Valid @RequestBody ShoppingCartInfo cartInfo ){
		//Gson gson = new Gson();
	   // Type listType = new ShoppingCartItem() {}.getType();

    	//ShoppingCartItem actualItem = gson.fromJson(item, ShoppingCartItem.class);

		ShoppingCart cart = new ShoppingCart();
		UUID actualId = UUID.fromString(cartInfo.getCartId());
	
		cart.setId(actualId);
	
		cart.setDateCreated(cartInfo.getDateCreated());
		ShoppingCart updatedCart =	shoppingCartService.addToCart(cart, cartInfo.getItem());
		EntityModel<ShoppingCart> entityModel = EntityModel.of(updatedCart);
		return entityModel;
	}
}
