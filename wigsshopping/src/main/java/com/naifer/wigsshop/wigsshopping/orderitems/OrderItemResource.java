package com.naifer.wigsshop.wigsshopping.orderitems;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;



@RestController
public class OrderItemResource {

	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("shop/order-items")
	public List<OrderItem> getItems(){
		return orderItemService.getItems();
	}
	
	@PutMapping("shop/order-items/update")
	public EntityModel<OrderItem> updateItem(@Valid @RequestBody OrderItem item) {
		OrderItem savedItem = orderItemService.updateItem(item);
		
		EntityModel<OrderItem> entityModel = EntityModel.of(savedItem);
		return entityModel;
	}
	
	@DeleteMapping("shop/order-items/delete/{id}")
	public void deleteCategory(@PathVariable UUID id) {
//		Optional<Product> product = shoppingItemService.getProductById(id);
//		if(product.isEmpty())
//			throw new ProductCategoryNotFoundException("id"+id);
		
		orderItemService.deleteProduct(id);
	}
}
