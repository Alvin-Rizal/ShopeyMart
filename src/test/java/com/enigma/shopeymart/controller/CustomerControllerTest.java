package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.dto.request.CustomerRequest;
import com.enigma.shopeymart.dto.response.CustomerResponse;
import com.enigma.shopeymart.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks //agar dibuatkan mock inject nya tidak perlu lewat postman untuk melakukan test
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse customerResponse = new CustomerResponse();

        when(customerService.create(customerRequest)).thenReturn(customerResponse);

        CustomerResponse actualResponse = customerController.createCustomer(customerRequest);

        assertEquals(customerResponse,actualResponse);
    }

    @Test
    void getAll() {
        List<CustomerResponse> expectedResponse = new ArrayList<>();

        when(customerService.getAll()).thenReturn(expectedResponse);

        List<CustomerResponse> actualResponse = customerController.getAll();

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void updateCustomer() {
        String customerId = "1";
        CustomerResponse expectedResponse = new CustomerResponse();
        CustomerRequest customerRequest = new CustomerRequest();

        when(customerService.update(customerRequest,customerId)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.updateCustomer(customerId,customerRequest);

        assertEquals(expectedResponse,actualResponse);

    }

    @Test
    void deleteCustomer() {
        String customerId = "1";
        customerController.deleteCustomer(customerId);
        verify(customerService,times(1)).delete(customerId);
    }

    @Test
    void getByIdCustomer() {
        String customerId = "1";
        CustomerResponse expectedResponse = new CustomerResponse();

        when(customerService.getById(customerId)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.getByIdCustomer(customerId);

        assertEquals(expectedResponse,actualResponse);
    }
}