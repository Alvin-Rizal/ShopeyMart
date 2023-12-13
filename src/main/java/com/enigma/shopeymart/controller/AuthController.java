package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.dto.request.AuthRequest;
import com.enigma.shopeymart.dto.response.RegisterResponse;
import com.enigma.shopeymart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ResponseBody
//Rest Controller merangkup @Controller dan @ResponseBody
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse response(@RequestBody AuthRequest authRequest) {
        return authService.registerCustomer(authRequest);
    }
}
