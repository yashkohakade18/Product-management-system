package edu.productmanagementsystem.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

	
	@NotBlank(message="Productname cannot be Blank")
	private String productname;
	
	@NotBlank(message="Brand cannot be Blank")
	private String brand;
	
	@Min(value=1,message="Price must be greater than 0")
	private Double price;

}
	
