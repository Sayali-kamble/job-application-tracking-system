package com.ats.application_tracking_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil,
                         CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Executes once per request.
     * Validates JWT and sets Authentication in SecurityContext.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 1. Read Authorization header
            String authHeader = request.getHeader("Authorization");

            // 2. Validate Bearer token format
            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);

                // 3. Validate token signature & expiry
                if (jwtUtil.validateToken(token)) {

                    // 4. Extract username (email)
                    String email = jwtUtil.extractEmail(token);

                    // 5. Authenticate only if context is empty
                    if (email != null &&
                            SecurityContextHolder.getContext().getAuthentication() == null) {

                        // 6. Load user from DB
                        UserDetails userDetails =
                                userDetailsService.loadUserByUsername(email);

                        // 7. Create authentication object
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authentication.setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(request)
                        );

                        // 8. Set authentication in SecurityContext
                        SecurityContextHolder.getContext()
                                .setAuthentication(authentication);

                        log.debug("JWT authenticated user: {}", email);
                    }
                }
            }
        } catch (Exception ex) {
            // IMPORTANT: Never crash the filter chain
            log.error("JWT authentication failed: {}", ex.getMessage());
        }

        // 9. Continue request
        filterChain.doFilter(request, response);
    }

    /**
     * Skip JWT filter for public endpoints
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/");
    }
}


/*
package com.ats.application_tracking_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    // Constructor-based dependency injection (best practice)
    public JwtAuthFilter(JwtUtil jwtUtil,
                         CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    */
/**
     * This method is executed once per HTTP request.
     * It validates JWT token and sets authentication
     * in Spring Security context if token is valid.
     *//*

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Extract Authorization header
        String authHeader = request.getHeader("Authorization");

        // Check if header is present and follows Bearer token format
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Extract JWT token (remove "Bearer ")
            String token = authHeader.substring(7);

            // Validate token (signature + expiration)
            if (jwtUtil.validateToken(token)) {

                // Extract user identifier (email) from token
                String email = jwtUtil.extractEmail(token);

                // Only authenticate if SecurityContext is empty
                if (email != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Load user details from database
                    UserDetails userDetails =
                            userDetailsService.loadUserByUsername(email);

                    // Create authentication token (password not required for JWT)
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Attach request-specific details (IP, session info)
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    // Set authentication in security context
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        }

        // Continue request processing
        filterChain.doFilter(request, response);
    }

    */
/**
     * Skip JWT validation for public endpoints
     * like login and registration.
     *//*

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/auth/login") ||
                path.startsWith("/auth/register");
    }
}

*/
