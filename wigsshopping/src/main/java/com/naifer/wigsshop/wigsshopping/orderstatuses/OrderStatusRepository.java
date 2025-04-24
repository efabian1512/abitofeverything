package com.naifer.wigsshop.wigsshopping.orderstatuses;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {
	
	  @Query("SELECT o FROM order_statuses o WHERE o.status = :status ORDER BY status LIMIT 1")
	 Optional<OrderStatus> findFirstByStatus(@Param("status") StatusTypes status);
}
