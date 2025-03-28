package com.naifer.wigsshop.wigsshopping.products;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;
import com.naifer.wigsshop.wigsshopping.utils.IImageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductService {
	
	private ProductRepository productRepository;
	private IImageService imageService;
	public ProductService(ProductRepository productRepository, IImageService imageService) {
		this.productRepository = productRepository;
		this.imageService = imageService;
	}
	
	public List<Product> getProducts(){
		List<Product> products = productRepository.findAll();
		
		if(products.isEmpty())
			return List.of();
		
		return products;
	}
	
	public Product saveProduct(double price, 
			 String productTitle, 
			 MultipartFile imageFile, ProductCategory category) throws IOException {

		Product product = new Product();

		String filePath = imageService.getImageFilePath(imageFile);

		product.setTitle(productTitle);
		product.setImagePath(filePath);
		product.setImageType(imageFile.getContentType());
		product.setPrice(price);
		product.setCategory(category);

		imageService.saveImage(imageFile);

		return productRepository.save(product);
}
	
	public void deleteProduct(UUID productId) {
		productRepository.deleteById(productId);
	}
	
public Optional<Product> getProductById(UUID id) {
		
		Optional<Product> savedProduct = productRepository.findById(id);
		
			
		return savedProduct;
	}
}
