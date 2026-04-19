package edu.productmanagementsystem.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.productmanagementsystem.dto.Request.ProductRequestDto;
import edu.productmanagementsystem.dto.Response.ApiResponseDto;
import edu.productmanagementsystem.dto.Response.ProductResponseDto;
import edu.productmanagementsystem.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class Productcontroller {

	private final ProductService productService;

	public Productcontroller(ProductService productService) {
	    this.productService = productService;
	}

    // ✅ GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProduct(@PathVariable int id) {
        return ResponseEntity.ok(
                ApiResponseDto.success(productService.getProductByid(id), "Product fetched successfully")
        );
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAllProducts() {
        return ResponseEntity.ok(
                ApiResponseDto.success(productService.getAllProducts(), "All products fetched")
        );
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductByNameAndBrand(
            @RequestParam String productName,
            @RequestParam String brand) {

        return ResponseEntity.ok(
                ApiResponseDto.success(
                        productService.getProductByNameAndbrand(productName, brand),
                        "Product fetched successfully"
                )
        );
    }

    // ✅ FILTER BY PRICE
    @GetMapping("/price")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductsByPrice(
            @RequestParam double price) {

        return ResponseEntity.ok(
                ApiResponseDto.success(
                        productService.getProductsByPrice(price),
                        "Products fetched successfully"
                )
        );
    }

    // ✅ PAGINATION + SORTING
    @GetMapping("/filter")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductResponseDto> products = productService.getProducts(pageable);

        return ResponseEntity.ok(
                ApiResponseDto.success(products.getContent(), "Products fetched successfully")
        );
    }

    // ✅ ADD
    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> addProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.created(productService.addProduct(dto), "Product added"));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> updateProduct(
            @PathVariable int id,
            @Valid @RequestBody ProductRequestDto dto) {

        return ResponseEntity.ok(
                ApiResponseDto.success(productService.updateProduct(id, dto), "Product updated")
        );
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ApiResponseDto<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ApiResponseDto.success(null, "Deleted successfully");
    }
}