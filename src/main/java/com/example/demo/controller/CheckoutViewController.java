package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutViewController {

    @Autowired
    private CartService cartService;

    // Sepet görünümünü HTML şablonuna döndüren metod
    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        Long cartId = 1L; // İhtiyacınıza göre değiştirebilirsiniz
        Cart cart = cartService.getCart(cartId);
        
        // Sepet verisini model'e ekliyoruz
        model.addAttribute("cart", cart);
        model.addAttribute("total", cartService.calculateTotal(cart));

        // checkout.html şablonunu döndürüyoruz
        return "checkout";
    }
}

