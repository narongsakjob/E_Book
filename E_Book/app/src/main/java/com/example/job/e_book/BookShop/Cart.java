package com.example.job.e_book.BookShop;

import java.util.ArrayList;


public class Cart {
    ArrayList<Book> books;
    public double sumPrice;
    ArrayList<String> cartList;

    public Cart() {
        books = new ArrayList<Book>();
        sumPrice = 0;
        cartList = new ArrayList<String>();
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void clear() {
        this.sumPrice = 0;
        cartList.clear();
        books.clear();
    }

    public ArrayList<String> getDummyCart(){
        return cartList;
    }

    public double getSumPrice() {
        return this.sumPrice;
    }

    public void addDummyCart(String book){
        cartList.add(book);
    }

    public void addToCart(Book book) {
        books.add(book);
        sumPrice+=book.getPrice();
    }
}
