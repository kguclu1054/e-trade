package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    // Sepet toplamını döndüren metod
    @GetMapping("/total")
    public ResponseEntity<Double> getCartTotal() {
        // Burada, cartId parametresi alabiliriz (veya mevcut cart'ı alabiliriz)
        // Örnek olarak, cartId'yi hardcode ediyorum:
        Long cartId = 1L; // İhtiyacınıza göre değiştirin
        Cart cart = cartService.getCart(cartId);
        
        // Sepetin toplam fiyatını hesapla
        double total = cartService.calculateTotal(cart);
        
        return ResponseEntity.ok(total);
    }

    // Sepet görünümünü döndüren metod
    @GetMapping("/view")
    public ResponseEntity<Cart> getCart() {
        // Burada, cartId parametresi alabiliriz (veya mevcut cart'ı alabiliriz)
        Long cartId = 1L; // İhtiyacınıza göre değiştirin
        Cart cart = cartService.getCart(cartId);
        
        return ResponseEntity.ok(cart);
    }
}

