package com.example.estateagency1.Service.Impl;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PropertySession {
    public void addToCart(Long id,  HttpSession session) {
//        HashMap<Long, OrderLine> cart;
//
//        var rawCart = session.getAttribute("CART");
//
//        if (rawCart instanceof HashMap) {
//            cart = (HashMap<Long, OrderLine>) rawCart;
//        } else {
//            cart = new HashMap<>();
//        }
//
//        Optional<Product> product = productRepository.findById(id);
//        if (product.isPresent()) {
//            OrderLine orderLine = cart.get(id);
//            if (orderLine == null) {
//                cart.put(id, new OrderLine(product.get(), 1));
//            } else {
//                orderLine.increaseByOne();
//                cart.put(id, orderLine);
//            }
//        }
//
//        session.setAttribute("CART", cart);
    }
}
