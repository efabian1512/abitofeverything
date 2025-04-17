package com.naifer.wigsshop.wigsshopping.products;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.ProductNotFoundException;
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
			
			return products.stream()
				.map(product -> {
					try {
						product.setProductImage(imageService.getImage(product));
					} catch (IOException e) {
						e.printStackTrace();
					}
					return product;
					
				}).toList();
	}
	
	public Product saveProduct(double price, 
			 String productTitle, 
			 MultipartFile imageFile, ProductCategory category) throws IOException {

		Product product = new Product();

		String filePath = imageService.getImageFilePath(imageFile);

		product.setImageName(imageFile.getOriginalFilename());
		product.setTitle(productTitle);
		product.setImagePath(filePath);
		product.setImageType(imageFile.getContentType());
		product.setPrice(price);
		product.setCategory(category);

		imageService.saveImage(imageFile);

		return productRepository.save(product);
}
	
//	public void deleteProduct(UUID productId) {
//		productRepository.deleteById(productId);
//	}
	
	public String deleteProduct(UUID id) {
		Optional<Product> product = getRawProduct(id);
		if(product.isEmpty())
			throw new ProductNotFoundException("id"+id);
		
		
		
		Product retrievedProduct = product.get();
		
		productRepository.delete(retrievedProduct);
		
		imageService.deleteImage(retrievedProduct.getImagePath());
		
		return "Product succesfully deleted.";
		
	}
	
	private Optional<Product> getRawProduct(UUID id) {
		return productRepository.findById(id);
	}
	
	public Product getProductById(UUID id) throws IOException {
		
		Optional<Product> savedProduct = productRepository.findById(id);
		
		if(savedProduct.isEmpty())
			throw new ProductNotFoundException("id"+id);
		
		Product retrievedProduct = savedProduct.get();
		
		byte [] image = imageService.getImage(retrievedProduct);
		
		retrievedProduct.setProductImage(image);
			
		return retrievedProduct;
	}
	
	public Product updateProduct(Product product, MultipartFile imageFile) throws IOException {
		
		Optional<Product> savedProduct = productRepository.findById(product.getId());
		
		
		if(savedProduct.isEmpty())
			throw new ProductNotFoundException("id"+product.getId());
		
		Product existingProduct = savedProduct.get();
		product.setImageName(imageFile.getOriginalFilename());
		product.setImagePath(imageService.getImageFilePath(imageFile));
		product.setImageType(imageFile.getContentType());
		
		if(existingProduct.getImagePath() != product.getImagePath()) {
			imageService.deleteImage(existingProduct.getImagePath());
			imageService.saveImage(imageFile);
		} 
		
		Product updatedProduct = productRepository.save(product);
		
		updatedProduct.setProductImage(imageService.getImage(updatedProduct));
		
		return updatedProduct;
	}
}
