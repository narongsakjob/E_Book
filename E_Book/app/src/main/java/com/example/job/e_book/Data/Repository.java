package com.example.job.e_book.Data;

import com.example.job.e_book.BookShop.Book;

import java.util.List;
import java.util.Observable;


public abstract class Repository extends Observable {
    public abstract List<Book> getAllBooks();
    public abstract List<Book> getBooks(String bookName);


}
