package com.example.job.e_book.BookShop;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jittat on 27/4/2560.
 */

public class BookJSONDecoder {
    public static Book createFromJSONObject(JSONObject obj) {
        try {
            Book book = new Book(obj.getString("title"),
                    obj.getInt("id"),
                    obj.getDouble("price"),
                    obj.getInt("pub_year"),
                    obj.getString("img_url"));

            return book;
        } catch(JSONException ex) {
            return null;
        }
    }

    public static ArrayList<Book> createListFromJSONStr(String jsonStr) {
        ArrayList<Book> results = new ArrayList<Book>();

        try {
            JSONArray jsonBookArray = new JSONArray(jsonStr);

            for(int i=0; i < jsonBookArray.length(); i++) {
                JSONObject bookJson = jsonBookArray.getJSONObject(i);
                Book book = createFromJSONObject(bookJson);
                if(book != null) {
                    results.add(book);
                }
            }
        } catch(JSONException e) {
            return null;
        }
        return results;
    }
}

