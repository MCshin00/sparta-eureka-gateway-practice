package com.spartacoding.msa.product.service;


import com.spartacoding.msa.product.domain.Product;
import com.spartacoding.msa.product.dto.ProductDto;
import com.spartacoding.msa.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        // 초기 상품 데이터 로드
        productRepository.saveAll(List.of(
                Product.builder().name("Product 1").description("Description 1").price(10.0).build(),
                Product.builder().name("Product 2").description("Description 2").price(20.0).build(),
                Product.builder().name("Product 3").description("Description 3").price(30.0).build()
        ));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}

