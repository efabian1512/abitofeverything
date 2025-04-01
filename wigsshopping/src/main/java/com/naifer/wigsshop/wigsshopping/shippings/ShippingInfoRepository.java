package com.naifer.wigsshop.wigsshopping.shippings;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, UUID> {
	
}
