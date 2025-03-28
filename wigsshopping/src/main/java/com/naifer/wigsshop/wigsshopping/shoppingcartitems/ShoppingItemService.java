package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public ShoppingCartItem SaveItem(ShoppingCartItem item) {
		return itemRepository.save(item);
	}
}
