package com.enigma.shopeymart.dto.response;

import com.enigma.shopeymart.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterResponse {
    private String username;
    private String role;
//    private Customer customer;
}
