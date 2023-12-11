package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.dto.request.ProductRequest;
import com.enigma.shopeymart.dto.response.CustomerResponse;
import com.enigma.shopeymart.dto.response.ProductResponse;
import com.enigma.shopeymart.dto.response.StoreResponse;
import com.enigma.shopeymart.entity.Customer;
import com.enigma.shopeymart.entity.Product;
import com.enigma.shopeymart.entity.ProductPrice;
import com.enigma.shopeymart.entity.Store;
import com.enigma.shopeymart.repository.ProductPriceRepository;
import com.enigma.shopeymart.repository.ProductRepository;
import com.enigma.shopeymart.service.CustomerService;
import com.enigma.shopeymart.service.ProductPriceService;
import com.enigma.shopeymart.service.ProductService;
import com.enigma.shopeymart.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final ProductPriceRepository productPriceRepository;
    private  final ProductPriceService productPriceService;
    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .build();
        productRepository.save(product);
        return productResponse(product);
    }

    @Override
    public ProductResponse getById(String id) {
        Product checkProductExist = productRepository.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
        return productResponse(checkProductExist);
    }

    @Override
    public List<Product> getAll() {

        return productRepository.findAll();
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, String id) {
        Product checkProductExist = productRepository.findById(id).orElseThrow(()->new RuntimeException("Id Not Found, cant Update"));
        checkProductExist.setName(productRequest.getName());
        checkProductExist.setDescription(productRequest.getDescription());
        productRepository.save(checkProductExist);
        return productResponse(checkProductExist);
    }

    @Override
    public void delete(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }else throw new RuntimeException("Cant Delete, Id not Exist");

    }

    private ProductResponse productResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName(product.getName());
        productResponse.setProductId(product.getId());
        productResponse.setDescription(product.getDescription());
        return productResponse;
    }
    private ProductResponse getAllproductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName(product.getName());
        productResponse.setProductId(product.getId());
        productResponse.setDescription(product.getDescription());
        return productResponse;
    }
    @Transactional(rollbackOn = Exception.class) //memberlakukan transactional pada productResponse
    @Override
    public ProductResponse createProductAndProductPrice(ProductRequest productRequest) {
        Store store = storeService.getByID(productRequest.getStoreId().getId());
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .build();
        productRepository.saveAndFlush(product); //digunakan ketika ingin menyimpan secara synchronous
        ProductPrice productPrice = ProductPrice.builder()
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .isActive(true)
                .product(product)
                .store(store)
                .build();
        productPriceService.create(productPrice);
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(Store.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .noSiup(store.getNoSiup())
                        .build())
                .build();
    }
}
