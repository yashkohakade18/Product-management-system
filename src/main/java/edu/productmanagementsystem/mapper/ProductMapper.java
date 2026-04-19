package edu.productmanagementsystem.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.productmanagementsystem.dto.Request.ProductRequestDto;
import edu.productmanagementsystem.dto.Response.ProductResponseDto;
import edu.productmanagementsystem.entity.Product;

@Component
public class ProductMapper {

	@Autowired
	private ModelMapper mapper;
	
	 public ProductResponseDto toDto(Product product) {
	        return mapper.map(product, ProductResponseDto.class);
	    }

	    public Product toEntity(ProductRequestDto dto) {
	        return mapper.map(dto, Product.class);
	    }
	
}
