package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.orderitems.OrderItemService;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.users.UserDTO;

@Component
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	public List<OrderDTO> getOrders(){
		List<Order> orders = orderRepository.findAll();
		
		if(orders.isEmpty())
			return List.of();
		
		return orders.stream().map((order) -> {
			OrderDTO orderDTO = getOrderDTO(order);
			return orderDTO;
			
		}).toList();
	}
	
	public OrderDTO getOrder(UUID id) {
		Optional<Order> savedOrder = orderRepository.findById(id);
		
		
		
		if(savedOrder.isEmpty())
				throw new ProductCategoryNotFoundException("id"+ id);
		
		OrderDTO orderDTO = getOrderDTO(savedOrder.get());
		
		return orderDTO;
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
	
	private OrderDTO getOrderDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setAccountVerified(order.getUser().isAccountVerified());
		userDTO.setEmail(order.getUser().getEmail());
		userDTO.setId(order.getUser().getId());
		userDTO.setName(order.getUser().getName());
		userDTO.setRoles(order.getUser().getRoles());
		
		
		orderDTO.setDatePlaced(order.getDatePlaced());
		orderDTO.setUser(userDTO);
		orderDTO.setId(order.getId());
		orderDTO.setItems(orderItemService.getOrderItemsWithProductImage(order.getItems()));
		orderDTO.setShippingInfo(order.getShippingInfo());
		
		return orderDTO;
	}
	
}
