package com.naifer.wigsshop.wigsshopping.orders;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import com.naifer.wigsshop.wigsshopping.orderitems.OrderItem;
import com.naifer.wigsshop.wigsshopping.shippings.ShippingInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="Shop_Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name="date_placed")
	private Double datePlaced;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_id")
	private ShippingInfo shippingInfo;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_order_id", referencedColumnName = "id" )
	private List<OrderItem> items;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(UUID id, Double datePlaced, ShippingInfo shippingInfo, List<OrderItem> items) {
		super();
		this.id = id;
		this.datePlaced = datePlaced;
		this.shippingInfo = shippingInfo;
		this.items = items;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", datePlaced=" + datePlaced + ", shippingInfo=" + shippingInfo + ", items=" + items
				+ "]";
	}

}
