package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/cart/**").authenticated() // '/cart' altında tüm endpoint'ler için kimlik doğrulama gerektir
                    .requestMatchers("/").authenticated()  // Ana sayfa (/) için kimlik doğrulama gerektir
                    .anyRequest().permitAll() // Diğer sayfalara anonim erişim izni
            )
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login") // Özel giriş sayfası
                    .permitAll() // Giriş sayfasına herkesin erişmesine izin ver
                    .defaultSuccessUrl("/home", true) // Başarılı giriş sonrası yönlendirme
            )
            .logout(logout -> 
                logout
                    .permitAll() // Çıkış işlemi herkes için erişilebilir
                    .logoutUrl("/logout") // Çıkış URL'si
                    .logoutSuccessUrl("/login?logout") // Çıkış sonrası yönlendirme (login sayfası)
            )
            .exceptionHandling(exceptionHandling -> 
                exceptionHandling
                    .authenticationEntryPoint((request, response, authException) -> 
                        response.sendRedirect("/login?error") // Korunan bir kaynağa erişim sırasında hatalı giriş durumunda
                    )
            );
        return http.build();
    }
}
