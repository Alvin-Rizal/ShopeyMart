package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.constant.AppPath;
import com.enigma.shopeymart.dto.request.ProductRequest;
import com.enigma.shopeymart.dto.response.CommonResponse;
import com.enigma.shopeymart.dto.response.ProductResponse;
import com.enigma.shopeymart.entity.Product;
import com.enigma.shopeymart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class ProductController {
    private final ProductService productService;
    @PostMapping(AppPath.PRODUCT)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProductAndProductPrice(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<ProductResponse>builder().statusCode(HttpStatus.CREATED.value()).message("Successfully Created new Product")
                .data(productResponse).build());
    }
    @GetMapping(AppPath.PRODUCT)
    public List<Product> getAllProduct() {
        return productService.getAll();
    }
}
