package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatusRequest;
import com.naifer.wigsshop.wigsshopping.users.UserInfo;

import org.springframework.hateoas.EntityModel;

@RestController
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("shop/orders")
	public List<OrderDTO> getOrders(){
		return orderService.getOrders();
	}
	
	@GetMapping("shop/orders/{id}")
	public EntityModel<OrderDTO> getOrder(@PathVariable UUID id){
		
		OrderDTO order = orderService.getOrder(id);
		
		EntityModel<OrderDTO> entityModel = EntityModel.of(order);
		return entityModel;
	}
	
	@GetMapping("shop/orders/byUser/{id}")
	public List<OrderDTO> getOrdersByUser(@PathVariable UUID id){
		return orderService.getOrdersByUser(id);
	}
	
	@PostMapping("shop/orders/save")
	public EntityModel<Order>  saveOrder(@RequestBody Order order){
		Order savedOrder =	orderService.saveOrder(order);
		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(savedOrder.getId())
//				.toUri();

		EntityModel<Order> entityModel = EntityModel.of(savedOrder);
		return entityModel;
	}
	
	@PostMapping("shop/orders/update-status")
	public EntityModel<OrderDTO>  updateOrderStatus(@RequestBody OrderStatusRequest request){
		OrderDTO savedOrder =	orderService.updateOrderStatus(request.getStatus(), request.getOrderId());
		
		EntityModel<OrderDTO> entityModel = EntityModel.of(savedOrder);
		return entityModel;
	}
	
}
