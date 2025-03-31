package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.List;
import java.util.Optional;
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
public class ShoppingCartItemResource {

	@Autowired
	private ShoppingItemService shoppingItemService;
	
	@GetMapping("shop/items")
	public List<ShoppingCartItem> getItems(){
		return shoppingItemService.getItems();
	}
	
	@PutMapping("shop/items/update")
	public EntityModel<ShoppingCartItem> updateItem(@Valid @RequestBody ShoppingCartItem item) {
		ShoppingCartItem savedItem = shoppingItemService.updateItem(item);
		
		EntityModel<ShoppingCartItem> entityModel = EntityModel.of(savedItem);
		return entityModel;
	}
	
	@DeleteMapping("shop/items/delete/{id}")
	public void deleteCategory(@PathVariable UUID id) {
//		Optional<Product> product = shoppingItemService.getProductById(id);
//		if(product.isEmpty())
//			throw new ProductCategoryNotFoundException("id"+id);
		
		shoppingItemService.deleteProduct(id);
	}
}
