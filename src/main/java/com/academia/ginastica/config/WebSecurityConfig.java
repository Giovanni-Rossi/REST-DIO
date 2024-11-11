package com.academia.ginastica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.academia.ginastica.config.CustomAuthenticationSuccessHandler;
import com.academia.ginastica.security.UsuarioDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig { 
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    
    @Bean
    public UserDetailsService clienteDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider clienteAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clienteDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    } 
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/alunos", "/alunos/{d}").permitAll()
                    .requestMatchers("/avaliacoes", "/avaliacoes/aluno/{d}", "/avaliacoes/data/{d}").permitAll()
                    .requestMatchers("/avaliacoes/{d}").permitAll()
                    .requestMatchers("/matriculas", "/matriculas/{d}").permitAll()
                    .anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            /*.formLogin((form) -> form
                    .loginPage("/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll())*/
            .logout((logout) -> logout
                    .logoutSuccessUrl("/").permitAll());

        return http.build();
    }
    
    }
