package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.dto.request.CustomerRequest;
import com.enigma.shopeymart.dto.response.CustomerResponse;
import com.enigma.shopeymart.entity.Customer;
import com.enigma.shopeymart.repository.CustomerRepository;
import com.enigma.shopeymart.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .email(customerRequest.getEmail()).build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .name(customer.getName())
                .customerAddress(customer.getAddress())
                .phone(customer.getMobilePhone())
                .build();
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer checkCustomerExist = customerRepository.findById(id).orElseThrow(() ->new RuntimeException("Customer Not Found"));
        return CustomerResponse.builder()
                .name(checkCustomerExist.getName())
                .emailCustomer(checkCustomerExist.getEmail())
                .customerAddress(checkCustomerExist.getAddress())
                .build();
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::customerResponse).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse update(String id, CustomerRequest customerRequest) {
        Customer checkCustomerExist = customerRepository.findById(id).orElseThrow(() ->new RuntimeException("Customer Not Found"));
        checkCustomerExist.setName(customerRequest.getName());
        checkCustomerExist.setMobilePhone(customerRequest.getMobilePhone());
        checkCustomerExist.setEmail(customerRequest.getEmail());
        checkCustomerExist.setAddress(customerRequest.getAddress());
        customerRepository.save(checkCustomerExist);
        return CustomerResponse.builder()
                .name(checkCustomerExist.getName())
                .emailCustomer(checkCustomerExist.getEmail())
                .phone(checkCustomerExist.getMobilePhone())
                .build();
    }

    @Override
    public void delete(String id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }else throw new RuntimeException("ID not Exixt, Cant Delete");

    }

    private CustomerResponse customerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(customer.getName());
        customerResponse.setEmailCustomer(customer.getEmail());
        customerResponse.setCustomerAddress(customer.getAddress());
        return customerResponse;
    }
}
