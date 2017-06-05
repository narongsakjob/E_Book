package com.example.job.e_book.Main;

import android.content.Intent;

import com.example.job.e_book.BookShop.User;


public class UserPresenter {
    private UserView view;
    User user;
    public UserPresenter(UserView view, Intent intent) {
        this.view = view;
        user = new User();
        createUser(intent);
    }
    public void refill(double amount) {
        user.refill(amount);
        view.updateCash(user.checkCash());
    }

    public void showCart() {
        view.updateAll(user.cart.getDummyCart());
    }

    public void showCollection(){
        view.updateAll(user.checkCollection());
    }

    public void setCash(double cash){
        user.cash = cash;
    }

    public void createUser(Intent intent){
        int cartSize = Integer.parseInt(intent.getStringExtra("cartSize"));
        int collectionSize = Integer.parseInt(intent.getStringExtra("collectionSize"));
        double cash = Double.parseDouble(intent.getStringExtra("cash"));
        double sumPrice = Double.parseDouble(intent.getStringExtra("sumPrice"));

        for(int i=0;i<collectionSize;i++){
            user.addCollection(intent.getStringExtra("collection"+i));
        }

        for(int i=0;i<cartSize;i++){
            user.cart.addDummyCart(intent.getStringExtra("cartBook"+i));
        }

        setCash(cash);
        setSumPrice(sumPrice);
        view.updateCash(user.cash);
    }


    public void setSumPrice(double sumPrice){
        user.cart.sumPrice = sumPrice;
    }

    public double getCash() {
        return user.cash;
    }

    public void purchase(){
        user.purchase();
        view.updateAll(user.cart.getDummyCart());
        view.updateCash(user.cash);
    }
}
