package com.example.job.e_book.BookShop;

import java.util.ArrayList;


public class User {
    public Cart cart;
    public double cash;
    public ArrayList<String> collectionList;

    public User() {
        cart = new Cart();
        cash = 0;
        collectionList = new ArrayList<String>();
    }

    public double checkCash() {
        return this.cash;
    }

    public void addToCart(Book book) {
        cart.addToCart(book);
    }
    public void purchase() {
        if (cash>=cart.getSumPrice()) {
            cash -= cart.getSumPrice();
            for (int i = 0; i < cart.cartList.size(); i++) {
                collectionList.add(cart.cartList.get(i));
            }
            cart.clear();
        }
    }
    public void refill(double amount) {
        this.cash += amount;
    }
    public ArrayList<String> checkCollection() {
        return this.collectionList;
    }
    public void addCollection(String book) {
        collectionList.add(book);
    }

}
