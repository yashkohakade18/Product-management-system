package edu.productmanagementsystem.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
	
	private int id;
	private String productname;
	private String brand;
	private double price;

}
