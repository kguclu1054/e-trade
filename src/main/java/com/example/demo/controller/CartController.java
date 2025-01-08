package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Sepete ürün ekleme
    @PostMapping("/add/{cartId}")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartItem item, @PathVariable Long cartId) {
        // cartId'yi ekliyoruz
        Cart updatedCart = cartService.addItemToCart(item, cartId);
        return ResponseEntity.ok(updatedCart);
    }

    // Sepet toplamını gösterme
    @GetMapping("/total/{cartId}")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long cartId) {
        double total = cartService.calculateTotal(cartService.getCart(cartId));
        return ResponseEntity.ok(total);
    }

    // Sepeti getirme (İçerik ve toplam tutarı)
    @GetMapping("/view/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}
