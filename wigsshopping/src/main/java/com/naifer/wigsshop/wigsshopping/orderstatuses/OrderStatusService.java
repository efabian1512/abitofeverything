package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusService {
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;

	public List<OrderStatus> getStatusList() {
		List<OrderStatus> statusList = orderStatusRepository.findAll();
	
		if(statusList.isEmpty())
			return List.of();
		
		return statusList;
	}
	
	public OrderStatus saveOrderStatus(OrderStatus status) {
		return orderStatusRepository.save(status);
	}
	
	public OrderStatus findByStatusType(StatusTypes statustype) {
		
		Optional<OrderStatus> savedStatus =	orderStatusRepository.findFirstByStatus(statustype);
		
		if(savedStatus.isEmpty())
			throw new RuntimeException("Status with type "+statustype+ " not found.");
		
		return savedStatus.get();
	}
	
}
