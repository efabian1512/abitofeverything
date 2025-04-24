package com.naifer.wigsshop.wigsshopping.orderitems;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.utils.IImageService;

@Component
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository itemRepository;
	
	@Autowired
	private IImageService imageService;
	
	public List<OrderItem> getItems(){
		List<OrderItem> items = itemRepository.findAll();
		
		if(items.isEmpty())
			return List.of();
		
		return getOrderItemsWithProductImage(items);
	}
	
	public List<OrderItem> getOrderItemsWithProductImage(List<OrderItem> items) {
		return items.stream()
				.map(item -> {
					try {
						item.getProduct().setProductImage(imageService.getImage(item.getProduct()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					return item;
					
				}).toList();
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
	
	public void deleteAll() {
		itemRepository.deleteAll();
	}
}
