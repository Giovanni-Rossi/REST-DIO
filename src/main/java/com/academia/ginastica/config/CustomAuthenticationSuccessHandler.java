package com.academia.ginastica.config;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	/*
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
        // Lógica de redirecionamento personalizada
        String redirectUrl = null;

        // Verifique os papéis do usuário autenticado
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_CLIENTE")) {
                redirectUrl = "/clientes/home";
                break;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "/admins/home";
                break;
            } else if (authority.getAuthority().equals("ROLE_LOCADORA")) {
                redirectUrl = "/locadoras/home";
                break;
            }
        }

        // Redireciona o usuário para a URL determinada
        response.sendRedirect(redirectUrl);*/
    }
}
