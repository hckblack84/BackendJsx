package com.example.demo.security.filter;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.security.jwt.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();


        if (path.startsWith("/auth/") || path.startsWith("/h2-console/") || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);

                // ⭐ VALIDAR EL TOKEN (usando el nombre de método correcto)
                if (jwtService.validateToken(token)) {
                    // ⭐ EXTRAER USERNAME (usando el nombre de método correcto)
                    String username = jwtService.getUsernameFromToken(token);

                    // Evitar re-autenticar si ya está autenticado
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        // ⭐ CARGAR EL USUARIO
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        // ⭐ CREAR LA AUTENTICACIÓN
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } else {
                    System.out.println("❌ Token inválido");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
