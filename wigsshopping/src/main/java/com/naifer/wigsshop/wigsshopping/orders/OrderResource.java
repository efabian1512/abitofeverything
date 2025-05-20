package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatus;
import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatusRequest;
import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatusService;
import com.naifer.wigsshop.wigsshopping.orderstatuses.StatusTypes;
import com.naifer.wihsshop.generic.GenericResponse;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

@RestController
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderStatusService orderStatusService;

	@GetMapping("shop/orders")
	public List<OrderDTO> getOrders(){
		return orderService.getOrders();
	}
	
	@GetMapping("shop/orders/{id}")
	public EntityModel<OrderDTO> getOrder(@PathVariable String id){
		
		OrderDTO order = orderService.getOrder(id);
		
		EntityModel<OrderDTO> entityModel = EntityModel.of(order);
		return entityModel;
	}
	
	@GetMapping("shop/orders/byUser/{id}")
	public List<OrderDTO> getOrdersByUser(@PathVariable UUID id){
		return orderService.getOrdersByUser(id);
	}
	
	@PostMapping("shop/orders/save")
	public ResponseEntity<GenericResponse<Object>> saveOrder(@RequestBody Order order){
		
		Optional<Order> optionalSavedOrder = orderService.getOrderByPaymentId(order.getPaymentId());
		
		GenericResponse<Object> resp = new GenericResponse<Object>();
		
		
		
//		if(optionalSavedOrder.isPresent()) {
//			resp.setData("This order is already placed.");
//			resp.setSuccess(false);
//			return ResponseEntity.ok().body(resp);
//		}
		
		if(optionalSavedOrder.isPresent())
			throw new RuntimeException("This order is already placed.");
			
			//return "payment is already registered for this cart";
		
		OrderStatus initialStatus = orderStatusService.findByStatusType(StatusTypes.PROCESSING);
		
//		initialStatus.setId(UUID.fromString("863bad71-492c-47c1-884d-7d83bc5f9ad2"));
//		initialStatus.setStatus(StatusTypes.PROCESSING);
		order.setStatusInfo(initialStatus);
		
		Order savedOrder =	orderService.saveOrder(order);
		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(savedOrder.getId())
//				.toUri();
		EntityModel<Order> entityModel = EntityModel.of(savedOrder);
		resp.setData(entityModel);
		resp.setSuccess(true);
		return ResponseEntity.ok().body(resp);
	}
	
	@PutMapping("shop/orders/update-status")
	public EntityModel<OrderDTO>  updateOrderStatus(@RequestBody OrderStatusRequest request){
		OrderDTO savedOrder =	orderService.updateOrderStatus(request.getStatus(), request.getOrderId());
		
		EntityModel<OrderDTO> entityModel = EntityModel.of(savedOrder);
		return entityModel;
	}
	
}
