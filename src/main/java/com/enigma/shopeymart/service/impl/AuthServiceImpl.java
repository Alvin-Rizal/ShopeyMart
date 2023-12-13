package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.constant.ERole;
import com.enigma.shopeymart.dto.request.AuthRequest;
import com.enigma.shopeymart.dto.response.RegisterResponse;
import com.enigma.shopeymart.entity.Customer;
import com.enigma.shopeymart.entity.Role;
import com.enigma.shopeymart.entity.UserCredential;
import com.enigma.shopeymart.repository.UserCredentialRepository;
import com.enigma.shopeymart.service.AuthService;
import com.enigma.shopeymart.service.CustomerService;
import com.enigma.shopeymart.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final RoleService roleService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {
            //ToDo 1.Set Role
            Role role = Role.builder()
                    .name(ERole.ROLE_CUSTOMER)
                    .build();
            Role roleSaved = roleService.getOrSave(role);
            //ToDo: 2. Set Credential
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .role(roleSaved)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            //ToDo 3.Set Customer
            Customer customer = Customer.builder()
                    .userCredential(userCredential)
                    .address(request.getAddress())
                    .name(request.getName())
                    .mobilePhone(request.getPhone())
                    .email(request.getEmail())
                    .build();
            customerService.createNewCustomerResponse(customer);
//            customerService.createNewCustomer(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString()).build();
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Already Exist");
        }
    }
}
