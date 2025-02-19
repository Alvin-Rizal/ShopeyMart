package com.enigma.shopeymart.security;

import com.enigma.shopeymart.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    //validasi token ke spring dan execute program token lainnya . Digunakan OncePerRequestFilter untuk eksekusi cma 1x
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //Validasi Token JWT
            String headerAuth = request.getHeader("Authorization");
            String token = null;
            if (headerAuth !=null && headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);

            }
            if(token !=null && jwtUtil.verifyJwtToken(token)) {
                //set auth ke spring security
                Map<String,String> userInfo = jwtUtil.getUserInfoByToken(token);
                UserDetails user = userService.loadUserByUserId(userInfo.get("userId"));
                System.out.println("terverify");
                //Validasi Token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                //Menambahkan informasi berupa Ip Address ke host dalam bentuk Security
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());

                //Menyimpan Auth Ke Spring Context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e) {
            e.getMessage();
        }
        filterChain.doFilter(request,response);
    }
}
