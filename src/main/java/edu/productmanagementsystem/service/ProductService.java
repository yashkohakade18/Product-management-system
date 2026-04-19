package edu.productmanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import edu.productmanagementsystem.dto.Request.ProductRequestDto;
import edu.productmanagementsystem.dto.Response.ProductResponseDto;

public interface ProductService {

	ProductResponseDto getProductByid(int id);


	    List<ProductResponseDto> getAllProducts();

	    ProductResponseDto getProductByNameAndbrand(String Prodcutname, String brand);

	    List<ProductResponseDto> getProductsByPrice(double price);

	    Page<ProductResponseDto> getProducts(Pageable pageable);

	    ProductResponseDto addProduct(ProductRequestDto dto);

	    ProductResponseDto updateProduct(int id, ProductRequestDto dto);


	   
	    void deleteProduct(int id);



		



		
}
