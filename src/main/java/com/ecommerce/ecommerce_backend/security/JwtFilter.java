package com.ecommerce.ecommerce_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        System.out.println("JWT FILTER HIT: " + path);
        System.out.println("AUTH HEADER = " + authHeader);

        // Skip public endpoints
        if (path.startsWith("/auth")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.equals("/swagger-ui.html")
                || (path.startsWith("/products") && request.getMethod().equals("GET"))) {
            filterChain.doFilter(request, response);
            return;
        }

        // No token or invalid format
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7).trim();
        System.out.println("TOKEN = " + token);

        try {
            username = jwtUtil.extractUsername(token);
            System.out.println("USERNAME = " + username);
        } catch (Exception e) {
            System.out.println("ERROR extracting username: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("CURRENT AUTH = " + SecurityContextHolder.getContext().getAuthentication());

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            System.out.println("Authorities: " + userDetails.getAuthorities());

            if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Authentication SET ✅");
            } else {
                System.out.println("Token validation FAILED ❌");
            }
        }

        filterChain.doFilter(request, response);
    }
}