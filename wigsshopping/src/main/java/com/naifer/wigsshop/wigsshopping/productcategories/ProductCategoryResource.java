package com.naifer.wigsshop.wigsshopping.productcategories;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

import jakarta.validation.Valid;

@RestController
public class ProductCategoryResource {
	
	private ProductCategoryService productCategoryService;
	
	public ProductCategoryResource(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	@GetMapping("shop/categories")
	public List<ProductCategory> getCategories(){
		return productCategoryService.getCategories();
	}
	
	@GetMapping("shop/categories/{id}")
	public EntityModel<ProductCategory> getCategoryById(@PathVariable UUID id) throws IOException {
		
		Optional<ProductCategory> retrievedProductCategory = productCategoryService.getProductCategoryById(id);
		
		EntityModel<ProductCategory> entityModel = EntityModel.of(retrievedProductCategory.get());
		return entityModel;
	}
	
	@PostMapping("shop/categories/save")
	public ResponseEntity<ProductCategory> saveCategory(@Valid @RequestBody ProductCategory category) throws IOException {
		ProductCategory savedProductCategory = productCategoryService.saveProductCategory(category);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedProductCategory.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("shop/categories/delete/{id}")
	public void deleteCategory(@PathVariable UUID id) {
		Optional<ProductCategory> productCategory = productCategoryService.getProductCategoryById(id);
		if(productCategory.isEmpty())
			throw new ProductCategoryNotFoundException("id"+id);
		
		productCategoryService.deleteProductCategory(id);
	}
	
	@PutMapping("shop/categories/update")
	public EntityModel<ProductCategory> updateCategory(@Valid @RequestBody ProductCategory category) throws IOException {
		
		ProductCategory retrievedProductCategory = productCategoryService.updateProductCategory(category);
		
		EntityModel<ProductCategory> entityModel = EntityModel.of(retrievedProductCategory);
		return entityModel;
	}
	

}
