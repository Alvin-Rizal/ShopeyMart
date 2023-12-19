package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.dto.request.ProductRequest;
import com.enigma.shopeymart.dto.response.ProductResponse;
import com.enigma.shopeymart.dto.response.StoreResponse;
import com.enigma.shopeymart.entity.Product;
import com.enigma.shopeymart.entity.ProductPrice;
import com.enigma.shopeymart.repository.ProductRepository;
import com.enigma.shopeymart.service.ProductPriceService;
import com.enigma.shopeymart.service.ProductService;
import com.enigma.shopeymart.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductPriceService productPriceService = mock(ProductPriceService.class);
    private final StoreService storeService = mock(StoreService.class);
    private final ProductService productService = new ProductServiceImpl(productRepository,storeService,productPriceService);

    @BeforeEach
    public void setUp(){
        //reset untuk mock behavior. Mengatur ulang status dari object palsu yang dibuat ke keadaan awalnya sehingga dapat digunakan kedalam pengujian lainnya tanpa harus inisialisai lagi
        reset(productRepository,storeService,productPriceService);
    }

    @Test
    void createProductAndProductPrice() {
        //store Request
        StoreResponse dummyStore = new StoreResponse();
        dummyStore.setId("1");
        dummyStore.setStoreName("Berkah Selalu");
        dummyStore.setNoSiup("123");

        //dummy create product
        when(storeService.getById(anyString())).thenReturn(dummyStore);
        Product saveProduct = new Product();
        saveProduct.setId("productId");
        saveProduct.setName("Oleo");
        saveProduct.setDescription("Hitam Manis");

        //save dummy product
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(saveProduct);

        //data dummy product request
        ProductRequest dummyProductRequest = mock(ProductRequest.class);

        //compare product name dan product desc dengan data yang di save
        when(dummyProductRequest.getProductName()).thenReturn(saveProduct.getName());
        when(dummyProductRequest.getDescription()).thenReturn(saveProduct.getDescription());
        when(dummyProductRequest.getProductId()).thenReturn(saveProduct.getId());
        when(dummyProductRequest.getStoreId()).thenReturn(dummyStore);

        ProductResponse productResponse = productService.createProductAndProductPrice(dummyProductRequest);

        //validate response
        assertNotNull(productResponse);
        assertEquals(saveProduct.getName(),productResponse.getProductName());

        //validate that the product price was set correct
        assertEquals(dummyProductRequest.getPrice(),productResponse.getPrice());
        assertEquals(dummyProductRequest.getStock(),productResponse.getStock());

        //validate interaction with store
        assertEquals(dummyStore.getId(),productResponse.getStore().getId());

        //verify interaction with store
        verify(storeService).getById(anyString());
        verify(productRepository).saveAndFlush(any(Product.class));
        verify(productPriceService).create(any(ProductPrice.class));

    }
}