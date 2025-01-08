package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Şifrelerin güvenli bir şekilde saklanması için şifreleme kullanılıyor
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // CSRF korumasını devre dışı bırakıyoruz (API uygulaması ise açık bırakılabilir)
            .authorizeRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/login", "/signup").permitAll() // Login ve Signup sayfalarına herkesin erişmesi sağlanır
                    .requestMatchers("/perform_signup").permitAll()  // Signup işlemi için özel izin verilir
                    .anyRequest().authenticated() // Diğer sayfalara kimlik doğrulama gereksinimi
            )
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login")  // Login sayfası
                    .permitAll() // Login sayfasına herkesin erişmesi sağlanır
                    .defaultSuccessUrl("/home", true) // Başarılı giriş sonrası yönlendirme
            )
            .logout(logout -> 
                logout
                    .permitAll() // Çıkış işlemi herkes için erişilebilir
                    .logoutUrl("/logout") // Çıkış URL'si
                    .logoutSuccessUrl("/login?logout") // Çıkış sonrası yönlendirme
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

