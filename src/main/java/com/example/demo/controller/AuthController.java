package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    // Login sayfasını döndüren metod
    @GetMapping("/login")
    public String showLoginPage() {
        return "loginPage"; // loginPage.html dosyasını döndürür
    }

    // Signup sayfasını döndüren metod
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signupPage"; // signupPage.html dosyasını döndürür
    }

    // Login işlemini gerçekleştiren metod
    @PostMapping("/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password) {
        // Burada giriş işlemi yapılabilir. Örneğin:
        // Eğer giriş başarılıysa:
        if (username.equals("admin") && password.equals("admin123")) {  // Bu kısmı kendi doğrulamanızla değiştirebilirsiniz
            return "redirect:/index"; // Başarılı giriş sonrası index sayfasına yönlendir
        } else {
            // Başarısız giriş durumunda tekrar login sayfasına döner
            return "redirect:/login?error"; 
        }
    }

    // Signup işlemini gerçekleştiren metod
    @PostMapping("/perform_signup")
    public String performSignup(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        // Şifrelerin eşleşip eşleşmediğini kontrol et
        if (!password.equals(confirmPassword)) {
            return "redirect:/signup?error";  // Hatalı şifre durumu
        }
        // Burada kullanıcı kaydı yapılabilir. Örneğin bir kullanıcı servisi kullanarak veritabanına kaydedebilirsiniz.
        
        // Başarılı kayıt sonrası kullanıcıyı login sayfasına yönlendirme
        return "redirect:/login";  
    }

    // Index sayfasına yönlendiren metod
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";  // index.html dosyasını döndürür
    }

    // Logout işlemi
    @GetMapping("/logout")
    public String logout() {
        // Çıkış işlemi yapabilirsiniz. Spring Security ile logout yapılabilir veya basitçe yönlendirme yapılabilir.
        return "redirect:/login";  // Logout sonrası login sayfasına yönlendirme
    }
}
