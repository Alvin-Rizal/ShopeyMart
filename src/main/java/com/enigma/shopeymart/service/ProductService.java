package com.enigma.shopeymart.service;

import com.enigma.shopeymart.dto.request.ProductRequest;
import com.enigma.shopeymart.dto.response.CustomerResponse;
import com.enigma.shopeymart.dto.response.ProductResponse;
import com.enigma.shopeymart.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Product getById(String id);

    Product update(Product product);

    void delete(String id);


    ProductResponse createProductAndProductPrice(ProductRequest productRequest);

    Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
}
