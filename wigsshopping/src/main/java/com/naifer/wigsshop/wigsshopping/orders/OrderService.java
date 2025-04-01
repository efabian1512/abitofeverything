package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

@Component
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getOrders(){
		
		List<Order> orders = orderRepository.findAll();
		
		if(orders.isEmpty())
			return List.of();
		
		return orders;
	}
	
	public Order getOrder(UUID id) {
		Optional<Order> savedOrder = orderRepository.findById(id);
		
		if(savedOrder.isEmpty())
				throw new ProductCategoryNotFoundException("id"+ id);
		
		return savedOrder.get();
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Order order) {
		Optional<Order> savedOrder = orderRepository.findById(order.getId());
		if(savedOrder.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ order.getId());
		
		return orderRepository.save(order);
	}
}
