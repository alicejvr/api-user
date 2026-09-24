package fr.campus.apiuser.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Récupère l'en-tête Authorization
        String authHeader = request.getHeader("Authorization");

        // Vérifie qu'il contient "Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Extrait le JWT
            String token = authHeader.substring(7);

            // Vérifie que le JWT est valide
            if (jwtService.isTokenValid(token)) {

                // Récupère le username dans le JWT
                String username = jwtService.extractUsername(token);

                // Récupère les rôles dans le JWT
                List<String> roles = jwtService.extractRoles(token);

                // Crée l'authentification
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                roles.stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .toList()
                        );

                // Enregistre l'utilisateur comme authentifié
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // Laisse la requête continuer
        filterChain.doFilter(request, response);
    }
}