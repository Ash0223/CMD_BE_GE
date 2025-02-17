package com.tgl.cmd.appointments.model;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class JwtTokenValidatorFilter extends OncePerRequestFilter {
 
    @Autowired
    private RestTemplate restTemplate;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
 
        String authHeader = request.getHeader("Authorization");
 
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
 
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        System.out.println("++++++++++++++++++++++++++++++++++++++" +  token);
//        String authServiceUrl = "http://localhost:8080/api/auth/validate";
        String authServiceUrl = "https://cmd-authentication-be-service.azurewebsites.net/api/auth/validate";

        System.out.println("Before Try catch");
        try {
            // Create request headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
//            System.out.println("22222222222222222222222" + requestEntity);
//            System.out.println("33333333333333333333333" + headers);
            // Send validation request to Authentication service
            System.out.println("------------------------------Doctor " + requestEntity);
            ResponseEntity<Boolean> validationResponse = restTemplate.exchange(
                    authServiceUrl,
                    HttpMethod.POST,
                    requestEntity,
                    Boolean.class 
            ); 
            System.out.println("_______________________validationResponse" + validationResponse);
 
            // Check if token is invalid
            if (Boolean.FALSE.equals(validationResponse.getBody())) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                System.out.println("============================================="+Boolean.FALSE.equals(validationResponse.getBody()));
                return;
            }
 
            // Set authentication context (assuming valid user)
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(token, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            	System.out.println("AAAAAAAAAAAAAAAAA" + authentication);
        } catch (Exception e) {
        	e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
 
        filterChain.doFilter(request, response);
    }
}