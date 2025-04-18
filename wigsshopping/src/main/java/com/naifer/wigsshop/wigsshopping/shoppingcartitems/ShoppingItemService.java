package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductNotFoundException;
import com.naifer.wigsshop.wigsshopping.utils.IImageService;

@Component
public class ShoppingItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private IImageService imageService;
	
	public List<ShoppingCartItem> getItems(){
		List<ShoppingCartItem> items = itemRepository.findAll();
		
		if(items.isEmpty())
			return List.of();
		
//		return items.stream()
//					.map(item -> {
//						ShoppingCartItemDTO itemDTO = new ShoppingCartItemDTO();
//						
//						itemDTO.setPrice(item.getProduct().getPrice());
//						itemDTO.setId(item.getId());
//						itemDTO.setQuantity(item.getQuantity());
//						itemDTO.setTitle(item.getProduct().getTitle());
//						itemDTO.setProductId(item.getProduct().getId());
//						try {
//							itemDTO.setProductImage(imageService.getImage(item.getProduct()));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						
//						return itemDTO;
//					}).toList();
		return items;
	}
	
	public ShoppingCartItem saveItem(ShoppingCartItem item) {
		return itemRepository.save(item);
	}
	
	public ShoppingCartItem updateItem(ShoppingCartItem item) {
		Optional<ShoppingCartItem> savedItem = itemRepository.findById(item.getId());
		
		if(savedItem.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ item.getId());
		
		return itemRepository.save(item);
	}
	
	public String deleteItem(UUID itemId) {
		Optional<ShoppingCartItem> savedItem = itemRepository.findById(itemId);
		
		if(savedItem.isEmpty())
			throw new ProductNotFoundException("id"+itemId);
		
		itemRepository.deleteById(itemId);
		
		return "Item successfully deleted.";
	}
}
