package edu.productmanagementsystem.service.implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.productmanagementsystem.dto.Request.ProductRequestDto;
import edu.productmanagementsystem.dto.Response.ProductResponseDto;
import edu.productmanagementsystem.entity.Product;
import edu.productmanagementsystem.exception.ResourceNotFoundException;
import edu.productmanagementsystem.mapper.ProductMapper;
import edu.productmanagementsystem.repository.ProductRepository;
import edu.productmanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceimpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // 🔹 Common method
    private Product getProductOrThrow(int id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }

    // ✅ GET BY ID
    @Override
    public ProductResponseDto getProductByid(int id) {
        return productMapper.toDto(getProductOrThrow(id));
    }

    // ✅ GET ALL
    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    // ✅ SEARCH (name + brand)
    @Override
    public ProductResponseDto getProductByNameAndbrand(String productName, String brand) {
        Product product = productRepository.findByProductnameAndBrand(productName, brand);

        if (product == null) {
            throw new ResourceNotFoundException(
                    "Product not found with name: " + productName + " and brand: " + brand
            );
        }

        return productMapper.toDto(product);
    }

    // ✅ FILTER BY PRICE
    @Override
    public List<ProductResponseDto> getProductsByPrice(double price) {
        List<Product> products = productRepository.findByPrice(price);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found with price: " + price);
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    // ✅ PAGINATION
    @Override
    public Page<ProductResponseDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    // ✅ ADD
    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    // ✅ UPDATE FULL
    @Override
    public ProductResponseDto updateProduct(int id, ProductRequestDto dto) {
        Product product = getProductOrThrow(id);

        product.setProductname(dto.getProductname());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());

        return productMapper.toDto(productRepository.save(product));
    }

  

    // ✅ DELETE
    @Override
    public void deleteProduct(int id) {
        Product product = getProductOrThrow(id);
        productRepository.delete(product);
    }
    
    
}