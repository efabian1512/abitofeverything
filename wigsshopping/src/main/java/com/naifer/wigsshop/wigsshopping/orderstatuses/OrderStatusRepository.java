package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {

}
