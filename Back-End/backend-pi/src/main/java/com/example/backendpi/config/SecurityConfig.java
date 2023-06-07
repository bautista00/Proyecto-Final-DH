package com.example.backendpi.config;

import com.example.backendpi.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.example.backendpi.domain.Role.ADMIN;
import static com.example.backendpi.domain.Role.USER;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/sign-up").permitAll()
                                .requestMatchers(HttpMethod.GET, "/hello/user")
                                .hasAnyAuthority(USER.name(), ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/hello/admin").hasAuthority(ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/swagger-ui/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/v3/api-docs/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority(ADMIN.name())
                                .anyRequest().authenticated())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    
//    darle los permisos al bucket para que pueda funcionar "/s3/**" en un matchers 
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}