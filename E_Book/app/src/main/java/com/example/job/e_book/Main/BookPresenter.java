package com.example.job.e_book.Main;

import com.example.job.e_book.BookShop.Book;
import com.example.job.e_book.BookShop.User;
import com.example.job.e_book.Data.JsonRepository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class BookPresenter implements Observer{
    JsonRepository repo;
    private BookView view;
    User user;

    public BookPresenter(BookView view){
        this.view = view;
        this.user = new User();
    }

    public void listAllBook(){
        repo = new JsonRepository();
        repo.addObserver(this);
        repo.fetchAllBooks();
    }

    public void search(String text,String filter) {
        List<Book> books = repo.getBooks(text);
        books = sortBook(books, filter);
        view.updateAll(books);
    }

    public void addToCart(int position) {
        user.addToCart(repo.getBookById(position));
    }

    public List<Book> sortBook(List<Book> books,String filter) {
        Book temp;
        if(filter.equals("Searching by Year")){
            for (int i = 0;i<books.size()-1;i++) {
                for (int j = i+1;j<books.size();j++) {
                    if (books.get(i).getPub_year() > books.get(j).getPub_year()) {
                        temp = books.get(j);
                        books.set(j,books.get(i));
                        books.set(i,temp);
                    }
                }
            }
        } else {
            for (int i = 0;i<books.size()-1;i++) {
                for (int j = i+1;j<books.size();j++) {
                    if (books.get(i).getTitle().charAt(0) > books.get(j).getTitle().charAt(0)) {
                        temp = books.get(j);
                        books.set(j, books.get(i));
                        books.set(i, temp);
                    }
                }
            }

        }

        return books;

    }

    public void setCash(double cash){
        user.cash = cash;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Book> books = repo.getAllBooks();
        view.updateAll(books);

    }


}

