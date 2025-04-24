package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="order_statuses")
public class OrderStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID )
	private UUID id;
	private StatusTypes status;
	
	public OrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderStatus(UUID id, StatusTypes status) {
		super();
		this.id = id;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public StatusTypes getStatus() {
		return status;
	}

	public void setStatus(StatusTypes status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderStatus [id=" + id + ", status=" + status + "]";
	}
}
