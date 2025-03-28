//package com.naifer.wigsshop.wigsshopping.orders;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity(name="Orders")
//public class Order {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
//	private UUID id;
//	
//	@Column(name="date_placed")
//	private LocalDateTime datePlaced;
//	
//	@Column(name="shipping_info")
//	private ShippingInfo shippingInfo;
//
//	public Order() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Order(UUID id, LocalDateTime datePlaced, ShippingInfo shippingInfo) {
//		super();
//		this.id = id;
//		this.datePlaced = datePlaced;
//		this.shippingInfo = shippingInfo;
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public LocalDateTime getDatePlaced() {
//		return datePlaced;
//	}
//
//	public void setDatePlaced(LocalDateTime datePlaced) {
//		this.datePlaced = datePlaced;
//	}
//
//	public ShippingInfo getShippingInfo() {
//		return shippingInfo;
//	}
//
//	public void setShippingInfo(ShippingInfo shippingInfo) {
//		this.shippingInfo = shippingInfo;
//	}
//
//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", datePlaced=" + datePlaced + ", shippingInfo=" + shippingInfo + "]";
//	}
//}
