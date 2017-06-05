package com.example.job.e_book.BookShop;

public class Book {
    private double price;
    private int id;
    private String title;
    private int pub_year;
    private String img_url;

    public Book(String title, int id, double price, int pub_year, String img_url){
        this.id = id;
        this.price = price;
        this.title = title;
        this.pub_year = pub_year;
        this.img_url = img_url;
    }


    public double getPrice(){
        return this.price;
    }

    public String getTitle(){
        return this.title;
    }

    public int getPub_year() {return this.pub_year;}

    public String toString() {
        return getTitle() + " " + getPub_year();
    }

}
