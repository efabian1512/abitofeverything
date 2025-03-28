package com.naifer.wigsshop.wigsshopping.productcategories.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductCategoryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProductCategoryNotFoundException(String message){
		super(message);
	}
}
