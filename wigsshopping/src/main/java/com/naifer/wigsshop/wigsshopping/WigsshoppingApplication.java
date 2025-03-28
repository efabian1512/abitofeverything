package com.naifer.wigsshop.wigsshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigsshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WigsshoppingApplication.class, args);
//		ConfigurableApplicationContext apc = SpringApplication.run(WigsshoppingApplication.class, args);
	
//		ProductService product1 = apc.getBean(ProductService.class);
//		ProductService product2 =  apc.getBean(ProductService.class);
//		
//		System.out.println(product1 + "===" +product2);
//		for(String s: apc.getBeanDefinitionNames()) {
//			System.out.println(s);
//		}
	}

}
