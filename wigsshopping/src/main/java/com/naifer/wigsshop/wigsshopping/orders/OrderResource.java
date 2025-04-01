package com.naifer.wigsshop.wigsshopping.orders;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

@RestController
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("shop/orders")
	public List<Order> getOrders(){
		return orderService.getOrders();
	}
	
	@GetMapping("shop/orders/{id}")
	public EntityModel<Order> getOrder(@PathVariable UUID id){
		
		Order order = orderService.getOrder(id);
		
		EntityModel<Order> entityModel = EntityModel.of(order);
		return entityModel;
	}
	
	@PostMapping("shop/orders/save")
	public ResponseEntity<Order> saveOrder(@RequestBody Order order){
		Order savedOrder =	orderService.saveOrder(order);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedOrder.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
}
