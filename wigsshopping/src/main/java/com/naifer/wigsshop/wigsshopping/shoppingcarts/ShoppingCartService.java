package com.naifer.wigsshop.wigsshopping.shoppingcarts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItem;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingCartItemDTO;
import com.naifer.wigsshop.wigsshopping.shoppingcartitems.ShoppingItemService;
import com.naifer.wigsshop.wigsshopping.utils.IImageService;

@Component
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private ShoppingItemService shoppingItemService;
	
	public List<ShoppingCart> getCarts(){
		List<ShoppingCart> carts = shoppingCartRepository.findAll();
		
		if(carts.isEmpty())
			return List.of();
		
		return carts;
	}
	
	public ShoppingCartDTO getCartById(UUID id) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(id);
		
		if(cart.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ id);
		
			ShoppingCart actualCart = cart.get();
			ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
			
		List<ShoppingCartItemDTO> actualItems =	actualCart.getItems()
					.stream()
					.map(item -> {
						ShoppingCartItemDTO itemDTO = new ShoppingCartItemDTO();
						
						itemDTO.setPrice(item.getProduct().getPrice());
						itemDTO.setId(item.getId());
						itemDTO.setQuantity(item.getQuantity());
						itemDTO.setTitle(item.getProduct().getTitle());
						itemDTO.setProductId(item.getProduct().getId());
						try {
							itemDTO.setProductImage(imageService.getImage(item.getProduct()));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						return itemDTO;
					}).toList();
		
		shoppingCartDTO.setDateCreated(actualCart.getDateCreated());
		shoppingCartDTO.setId(actualCart.getId());
		shoppingCartDTO.setItems(actualItems);
		
		return shoppingCartDTO;
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
	
	public String clearCart(UUID id) {
		Optional<ShoppingCart> savedCart = shoppingCartRepository.findById(id);
		if(savedCart.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ id);
		
		ShoppingCart actualCart = savedCart.get();
		
		shoppingItemService.deleteAllInCart(actualCart.getItems());
	
		return "Cart cleared.";
	}
	
}
