package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.UUID;

public class OrderStatusRequest {
	
	private UUID orderId;
	private OrderStatus status;
	
	public OrderStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderStatusRequest(UUID orderId, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID id) {
		this.orderId = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderStatusRequest [orderId=" + orderId + ", status=" + status + "]";
	}
}
