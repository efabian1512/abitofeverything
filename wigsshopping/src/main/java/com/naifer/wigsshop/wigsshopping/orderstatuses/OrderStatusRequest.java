package com.naifer.wigsshop.wigsshopping.orderstatuses;

public class OrderStatusRequest {
	
	private String orderId;
	private OrderStatus status;
	
	public OrderStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderStatusRequest(String orderId, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String id) {
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
