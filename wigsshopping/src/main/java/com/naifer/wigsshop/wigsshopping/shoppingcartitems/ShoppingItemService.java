package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

@Component
public class ShoppingItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<ShoppingCartItem> getItems(){
		List<ShoppingCartItem> items = itemRepository.findAll();
		
		if(items.isEmpty())
			return List.of();
		
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
	
	public void deleteProduct(UUID itemId) {
		itemRepository.deleteById(itemId);
	}
}
