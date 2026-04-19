package edu.productmanagementsystem.exception;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {

	
	
	

		
		private int statusCode;
		private String message;
		private String error;
		private String path;
		private LocalDateTime timestamp;
		
		

	}

