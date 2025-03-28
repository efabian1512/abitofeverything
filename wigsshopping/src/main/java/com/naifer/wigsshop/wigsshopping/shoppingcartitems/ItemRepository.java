package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ShoppingCartItem, UUID>  {

}
