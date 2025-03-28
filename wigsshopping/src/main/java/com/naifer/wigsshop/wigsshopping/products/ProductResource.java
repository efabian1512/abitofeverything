package com.naifer.wigsshop.wigsshopping.products;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

import jakarta.validation.Valid;

@RestController
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("shop/products")
	public List<Product> getProducts(){
		return productService.getProducts();
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
		System.out.println(category);
		
		
		

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					   .path("/{id}")
					   .buildAndExpand(savedProduct.getId())
					   .toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("shop/products/delete/{id}")
	public void deleteCategory(@PathVariable UUID id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isEmpty())
			throw new ProductCategoryNotFoundException("id"+id);
		
		productService.deleteProduct(id);
	}
}
