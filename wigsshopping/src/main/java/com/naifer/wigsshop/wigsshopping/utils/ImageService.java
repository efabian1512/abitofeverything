package com.naifer.wigsshop.wigsshopping.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.naifer.wigsshop.wigsshopping.products.Product;

@Component
public class ImageService implements IImageService {
	
	private final String IMAGES_FOLDER_PATH = "/Users/edwinfabian/Documents/abit-of-everything-shop-images/";
	
	
	public void saveImage(MultipartFile imageFile) throws IOException {
		imageFile.transferTo(new File(this.getImageFilePath(imageFile)));
	}
	
	public byte [] getImage(Product product) throws IOException {
		
		String filePath = product.getImagePath();
		
		byte[] image = Files.readAllBytes(new File(filePath).toPath());
			return image;	
	}
	
	public byte[] compressImage(byte[] data) {
		
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		
		byte[] tmp = new byte[4*1024];
		while(!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}
		
		try {
			outputStream.close();
		} catch (Exception ignored) {
			
		}
		
		return outputStream.toByteArray();
	}
	
	public byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[]tmp = new byte[4*1024];
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
		}catch (Exception ingored) {
		}
		
		return outputStream.toByteArray();
	}
	
	public String getImageFilePath(MultipartFile imageFile) {
		return IMAGES_FOLDER_PATH + imageFile.getOriginalFilename();
	}
	
	public void deleteImage(String imagePath) {
		File previousImage = new File(imagePath);
		previousImage.delete();
	}
	
}
