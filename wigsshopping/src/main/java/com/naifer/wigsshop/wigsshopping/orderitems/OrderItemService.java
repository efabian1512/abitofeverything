package com.naifer.wigsshop.wigsshopping.orderitems;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

@Component
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository itemRepository;
	
	public List<OrderItem> getItems(){
		List<OrderItem> items = itemRepository.findAll();
		
		if(items.isEmpty())
			return List.of();
		
		return items;
	}
	
	public OrderItem saveItem(OrderItem item) {
		return itemRepository.save(item);
	}
	
	public OrderItem updateItem(OrderItem item) {
		Optional<OrderItem> savedItem = itemRepository.findById(item.getId());
		
		if(savedItem.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ item.getId());
		
		return itemRepository.save(item);
	}
	
	public void deleteProduct(UUID itemId) {
		itemRepository.deleteById(itemId);
	}
}
