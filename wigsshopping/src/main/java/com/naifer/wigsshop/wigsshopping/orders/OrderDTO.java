package com.naifer.wigsshop.wigsshopping.orders;

import java.util.List;
import java.util.UUID;

import com.naifer.wigsshop.wigsshopping.orderitems.OrderItem;
import com.naifer.wigsshop.wigsshopping.orderstatuses.OrderStatus;
import com.naifer.wigsshop.wigsshopping.orderstatuses.StatusTypes;
import com.naifer.wigsshop.wigsshopping.shippings.ShippingInfo;
import com.naifer.wigsshop.wigsshopping.users.UserDTO;

public class OrderDTO {
	
	private UUID id;
	private UserDTO user;
	private Double datePlaced;
	private ShippingInfo shippingInfo;
	private List<OrderItem> items;
	private double total;
	private OrderStatus statusInfo;
	
	public OrderDTO() {
		super();
	}

	public OrderDTO(UUID id, UserDTO user, Double datePlaced, ShippingInfo shippingInfo, List<OrderItem> items,
			double total, OrderStatus statusInfo) {
		super();
		this.id = id;
		this.user = user;
		this.datePlaced = datePlaced;
		this.shippingInfo = shippingInfo;
		this.items = items;
		this.total = total;
		this.statusInfo = statusInfo;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(Double datePlaced) {
		this.datePlaced = datePlaced;
	}

	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public OrderStatus getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(OrderStatus statusInfo) {
		this.statusInfo = statusInfo;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", user=" + user + ", datePlaced=" + datePlaced + ", shippingInfo=" + shippingInfo
				+ ", items=" + items + ", total=" + total + ", status=" + statusInfo + "]";
	}
}
