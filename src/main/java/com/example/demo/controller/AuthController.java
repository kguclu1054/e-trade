package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

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
    public String performLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Kullanıcıyı veritabanında arama
        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/index"; // Başarılı giriş sonrası index sayfasına yönlendir
        } else {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre");
            return "loginPage"; // Başarısız giriş durumu, hata mesajı ile login sayfasına geri dön
        }
    }

    // Signup işlemini gerçekleştiren metod
    @PostMapping("/perform_signup")
    public String performSignup(@RequestParam String username, @RequestParam String email, 
                                 @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Şifreler uyuşmuyor.");
            return "signupPage"; // Hatalı şifre durumu, hata mesajı ile signup sayfasına geri dön
        }

        // Kullanıcı adı zaten varsa
        if (userService.findByUsername(username) != null) {
            model.addAttribute("error", "Kullanıcı adı zaten alınmış.");
            return "signupPage"; // Hata mesajı ile signup sayfasına geri dön
        }

        // Yeni kullanıcıyı oluşturma ve kaydetme
        User newUser = new User(username, password, email);
        userService.saveUser(newUser); // Kullanıcıyı veritabanına kaydet

        return "redirect:/login"; // Başarılı kayıt sonrası login sayfasına yönlendirme
    }

    // Index sayfasına yönlendiren metod
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";  // index.html dosyasını döndürür
    }

    // Logout işlemi
    @GetMapping("/logout")
    public String logout() {
        // Çıkış işlemi yapılabilir. Basitçe yönlendirme yapıyoruz.
        return "redirect:/login";  // Logout sonrası login sayfasına yönlendirme
    }
}

