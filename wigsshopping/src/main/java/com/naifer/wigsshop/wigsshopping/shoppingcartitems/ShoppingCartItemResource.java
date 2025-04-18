package com.naifer.wigsshop.wigsshopping.shoppingcartitems;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naifer.wigsshop.wigsshopping.auth.AuthToken;
import com.naifer.wihsshop.generic.GenericResponse;

import jakarta.validation.Valid;



@RestController
public class ShoppingCartItemResource {

	@Autowired
	private ShoppingItemService shoppingItemService;
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("shop/items")
	public List<ShoppingCartItem> getItems(){
		return shoppingItemService.getItems();
	}
	
	@PutMapping("shop/items/update")
	public  ResponseEntity<GenericResponse<String>> updateItem(@Valid @RequestBody ShoppingCartItem item) {
		shoppingItemService.updateItem(item);
		
		String message = "Product successfully updated.";
		
		if(item.getQuantity() == 0) {
		  message = "Item removed from cart, since quantity is 0.";
		  shoppingItemService.deleteItem(item.getId());
		}
		GenericResponse<String> resp = new GenericResponse<String>();
		
		resp.setData(message);
		resp.setSuccess(true);
		
		return ResponseEntity.ok().body(resp);
//		EntityModel<ShoppingCartItem> entityModel = EntityModel.of(savedItem);
		//return entityModel;
	}
	
	@DeleteMapping("shop/items/delete/{id}")
	public void deleteItem(@PathVariable UUID id) {	
		shoppingItemService.deleteItem(id);
	}
}
