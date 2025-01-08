package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * Sepete ürün ekler
     * @param item Sepete eklenecek ürün
     * @param cartId Sepetin ID'si
     * @return Güncellenmiş sepet
     */
    @Transactional
    public Cart addItemToCart(CartItem item, Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            item.setCart(cart);
            cartItemRepository.save(item);
            log.info("Item saved: " + item);
            
            cart.setTotalPrice(calculateTotal(cart));
            cartRepository.save(cart);
            log.info("Cart updated: " + cart);

            return cart;
        } else {
            throw new RuntimeException("Sepet bulunamadı, lütfen tekrar deneyin.");
        }
    }

    /**
     * Sepetteki tüm ürünlerin toplam fiyatını hesaplar
     * @param cart Sepet
     * @return Sepetin toplam fiyatı
     */
    public double calculateTotal(Cart cart) {
        // Sepetteki tüm ürünlerin fiyatını ve miktarını dikkate alarak toplam fiyat hesaplanıyor
        return cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * Sepeti ID ile bulur
     * @param cartId Sepetin ID'si
     * @return Sepet
     */
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));
    }
}
