package com.naifer.wigsshop.wigsshopping.productcategories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

}
