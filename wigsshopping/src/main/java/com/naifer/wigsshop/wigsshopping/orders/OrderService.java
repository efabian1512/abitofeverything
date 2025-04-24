package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.orderitems.OrderItemService;
import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatus;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wigsshop.wigsshopping.users.UserDTO;
import com.naifer.wigsshop.wigsshopping.users.UserInfo;

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
	
	
	public List<OrderDTO> getOrdersByUser(UUID userId) {
		
	List<Order> orders = orderRepository.getOrdersByUserId(userId);
		
		if(orders.isEmpty())
			return List.of();
		
		return orders.stream().map((order) -> {
			OrderDTO orderDTO = getOrderDTO(order);
			return orderDTO;
			
		}).toList();
	}
	
	public OrderDTO updateOrderStatus(OrderStatus statusInfo, UUID orderId) {
		Optional<Order> savedOrder = orderRepository.findById(orderId);
		if(savedOrder.isEmpty())
			throw new ProductCategoryNotFoundException("id"+ orderId);
		
		Order actualOrder = savedOrder.get();
		
		actualOrder.setStatusInfo(statusInfo);
		
		return getOrderDTO(orderRepository.save(actualOrder));
	}
	
	public void deleteAll() {
		orderRepository.deleteAll();
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
		orderDTO.setTotal(order.getTotal());
		orderDTO.setStatusInfo(order.getStatusInfo());
		
		return orderDTO;
	}
	
	
	
	
}
