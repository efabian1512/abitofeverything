package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderStatusResource {
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@GetMapping("shop/order/status-list")
	public List<OrderStatus> getStatusList() {
		return orderStatusService.getStatusList();
	}
	
	@PostMapping("shop/order/status/save")
	public EntityModel<OrderStatus> saveOrderStatus(@RequestBody OrderStatus status) {
		
		OrderStatus savedStatus = orderStatusService.saveOrderStatus(status);
		
		EntityModel<OrderStatus> entityModel = EntityModel.of(savedStatus);
		
		return entityModel;
	}
	

}
