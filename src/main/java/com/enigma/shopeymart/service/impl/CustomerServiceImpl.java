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
        return customerResponse(customer);
    }

    @Override
    public CustomerResponse getById(String id) {
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer != null) {
                return CustomerResponse.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .emailCustomer(customer.getEmail())
                        .customerAddress(customer.getAddress())
                        .build();
            }
            return null;
        }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::customerResponse).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest , String id) {
        Customer checkCustomerExist = customerRepository.findById(id).orElseThrow(() ->new RuntimeException("Customer Not Found"));
        checkCustomerExist.setName(customerRequest.getName());
        checkCustomerExist.setMobilePhone(customerRequest.getMobilePhone());
        checkCustomerExist.setEmail(customerRequest.getEmail());
        checkCustomerExist.setAddress(customerRequest.getAddress());
        customerRepository.save(checkCustomerExist);
        return customerResponse(checkCustomerExist);
    }

    @Override
    public void delete(String id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }else throw new RuntimeException("ID not Exist, Cant Delete");

    }

    private CustomerResponse customerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setName(customer.getName());
        customerResponse.setEmailCustomer(customer.getEmail());
        customerResponse.setCustomerAddress(customer.getAddress());
        return customerResponse;
    }
}
