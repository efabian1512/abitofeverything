package com.naifer.wigsshop.wigsshopping.productcategories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductCategoryNotFoundException;

@Component
public class ProductCategoryService {
	
	private ProductCategoryRepository productCategoryRepository;

	public ProductCategoryService(ProductCategoryRepository productCategoriesRepository) {
		super();
		this.productCategoryRepository = productCategoriesRepository;
	}
	
	public List<ProductCategory> getCategories(){
		List<ProductCategory> categories = productCategoryRepository.findAll();
		
		if(categories.isEmpty())
			return List.of();
		
		return categories;
	}
	
	public ProductCategory saveProductCategory(ProductCategory category) {
		return productCategoryRepository.save(category);
	}
	
	public void deleteProductCategory(UUID categoryId) {
		throwExceptionWhenEmptyById(categoryId);
		productCategoryRepository.deleteById(categoryId);
	}
	
	public ProductCategory updateProductCategory(ProductCategory category) {
		throwExceptionWhenEmptyById(category.getId());
		
		ProductCategory updatedProductCategory = productCategoryRepository.save(category);
		return updatedProductCategory;
		
	}
	
	public Optional<ProductCategory> getProductCategoryById(UUID id) {
		
		Optional<ProductCategory> savedCategory = productCategoryRepository.findById(id);
		
		throwExceptionWhenEmptyByObjetAndId(savedCategory, id);
			
		return savedCategory;
	}
	
	
	private void throwExceptionWhenEmptyById(UUID id) {
		
		Optional<ProductCategory> savedCategory = productCategoryRepository.findById(id);
		if(savedCategory.isEmpty())
			throw new ProductCategoryNotFoundException("id"+id);
	}
	
	private void throwExceptionWhenEmptyByObjetAndId(Optional<ProductCategory> savedCategory, UUID id) {
		
		if(savedCategory.isEmpty())
			throw new ProductCategoryNotFoundException("id"+id);
	}
	
}
