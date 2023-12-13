package com.enigma.shopeymart.security;

import com.enigma.shopeymart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter {
    //validasi token ke spring dan execute program token lainnya
    private final JwtUtil jwtUtil;
    private final UserService userService;

}
