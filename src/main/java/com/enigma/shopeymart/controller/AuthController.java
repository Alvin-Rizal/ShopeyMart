package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.constant.AppPath;
import com.enigma.shopeymart.dto.request.AuthRequest;
import com.enigma.shopeymart.dto.response.LoginResponse;
import com.enigma.shopeymart.dto.response.RegisterResponse;
import com.enigma.shopeymart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
//Rest Controller merangkup @Controller dan @ResponseBody
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public RegisterResponse response(@RequestBody AuthRequest authRequest) {
        return authService.registerCustomer(authRequest);
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public LoginResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
    @PostMapping("/admin")
    @CrossOrigin(origins = "http://localhost:5173")
    public RegisterResponse responseAdmin(@RequestBody AuthRequest authRequest) {
        return authService.registerAdmin(authRequest);
    }

}
