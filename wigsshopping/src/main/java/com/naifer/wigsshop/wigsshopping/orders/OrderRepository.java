package com.naifer.wigsshop.wigsshopping.orders;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.naifer.wigsshop.wigsshopping.users.UserInfo;

public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByUser(UserInfo user);
	
	  @Query("SELECT o FROM A_bit_Shopping_Orders o WHERE o.user.id = :userId")
	    List<Order> getOrdersByUserId(@Param("userId") UUID userId);
	  
	  Optional<Order> findByPaymentId(String paymentId);
}
