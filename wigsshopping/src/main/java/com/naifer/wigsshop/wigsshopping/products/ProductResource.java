package com.naifer.wigsshop.wigsshopping.products;

import java.io.IOException;
import java.net.URI;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naifer.wigsshop.wigsshopping.auth.AuthToken;
import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;
import com.naifer.wihsshop.generic.GenericResponse;

import jakarta.validation.Valid;

@RestController
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("shop/products")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> getProducts(){
		return productService.getProducts();
	}
	
	@GetMapping("shop/products/{id}")
	public EntityModel<Product> getProductById(@PathVariable UUID id) throws IOException {
		Product product = productService.getProductById(id);
	
		EntityModel<Product> entityModel = EntityModel.of(product);
		return entityModel;
	}

	@PostMapping("/shop/products/save")
	public  ResponseEntity<Product> saveProduct
	(@Valid @RequestParam("price") double price, 
			@Valid @RequestParam("title") String title, 
			@Valid @RequestParam("productImage") MultipartFile imageFile,
			@Valid @RequestParam("category") String category
			) throws IOException {
		
		ObjectMapper objectmapper = new ObjectMapper();
		
		ProductCategory productCategory = new ProductCategory();
		try {
			productCategory = objectmapper.readValue(category, ProductCategory.class);
		
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		}
		
		Product savedProduct = productService.saveProduct(price, title, imageFile, productCategory);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedProduct.getId())
					   .toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("shop/products/update")
	public EntityModel<Product> updateProduct(@Valid @RequestParam("price") Integer price, 
			@Valid @RequestParam("title") String productName, 
			@Valid @RequestParam("productImage") MultipartFile imageFile,
			@Valid @RequestParam("category") String category,
			@Valid @RequestParam("id") UUID id) throws IOException {
		
		Product product = new Product();
		
		product.setId(id);
		product.setTitle(productName);
		product.setPrice(price);
		
		ObjectMapper objectmapper = new ObjectMapper();
		
		ProductCategory productCategory = new ProductCategory();
		try {
			productCategory = objectmapper.readValue(category, ProductCategory.class);
			product.setCategory(productCategory);
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		}
		
		Product retrievedProduct = productService.updateProduct(product, imageFile);
		
		EntityModel<Product> entityModel = EntityModel.of(retrievedProduct);
		return entityModel;
	}
	
	@DeleteMapping("shop/products/delete/{id}")
	public ResponseEntity<GenericResponse<String>>  deleteProduct(@PathVariable UUID id)  throws IOException {
		String message = productService.deleteProduct(id);
		
	
		GenericResponse<String> genericResponse = new GenericResponse<String>();
		
		genericResponse.setData(message);
		genericResponse.setSuccess(true);
		
		return ResponseEntity.ok().body(genericResponse);
	}
}
