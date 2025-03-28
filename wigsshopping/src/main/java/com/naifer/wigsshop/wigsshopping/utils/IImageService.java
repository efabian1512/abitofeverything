package com.naifer.wigsshop.wigsshopping.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.naifer.wigsshop.wigsshopping.products.Product;

@Component
public interface IImageService {

	public void saveImage(MultipartFile imageFile) throws IOException;
	public byte [] getImage(Product product) throws IOException;
	public byte[] compressImage(byte[] data);
	public byte[] decompressImage(byte[] data);
	public String getImageFilePath(MultipartFile imageFile);
	public void deleteImage(String imagePath);
}
