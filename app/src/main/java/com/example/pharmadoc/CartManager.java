package com.example.pharmadoc;

import java.util.ArrayList;
public class CartManager {
    private static CartManager instance;
    private ArrayList<Product> cart;

    private CartManager() {
        cart = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice(); // Assuming getPrice() returns product price
        }
        return total;
    }
}