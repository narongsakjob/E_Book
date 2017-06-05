package com.example.job.e_book.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.job.e_book.BookShop.Book;
import com.example.job.e_book.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookView {

    BookPresenter presenter = null;
    String search = "Searching by Title";
    ListView resultListView;
    RadioGroup radioBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewHolders();
        if(presenter == null) {
            presenter = new BookPresenter(this);
        }
        presenter.listAllBook();

        radioBt = (RadioGroup) findViewById(R.id.radioGroup);
        radioBt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                search = rb.getText().toString();
            }
        });

        createUser();

    }
    private void createUser(){
        if(getIntent().getExtras() != null) {
            Intent intent = getIntent();
            int collectionSize = Integer.parseInt(intent.getStringExtra("collectionSize"));
            double cash = Double.parseDouble(intent.getStringExtra("cash"));

            for (int i = 0; i < collectionSize; i++) {
                presenter.user.addCollection(intent.getStringExtra("collection" + i));
            }

            presenter.setCash(cash);
        }

    }

    private void initViewHolders() {
        resultListView = (ListView) findViewById(R.id.list);
    }

    public void onClickSearch(View view) {
        EditText editText = (EditText)findViewById(R.id.text);
        String text = editText.getText().toString();

        presenter.search(text,search);
    }

    public void onClickUser(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        ArrayList<Book> cartBooks = presenter.user.cart.getBooks();
        ArrayList<String> collection = presenter.user.checkCollection();

        intent.putExtra("cartSize", cartBooks.size()+"");
        intent.putExtra("collectionSize", collection.size()+"");
        intent.putExtra("cash", presenter.user.cash+"");
        intent.putExtra("sumPrice", presenter.user.cart.getSumPrice()+"");
        for (int i = 0;i<collection.size();i++) {
            intent.putExtra("collection"+i,collection.get(i));
        }
        for (int i = 0;i<cartBooks.size();i++) {
            intent.putExtra("cartBook"+i,cartBooks.get(i).getTitle()+"\n"+ cartBooks.get(i).getPub_year()+ "\nPrice: " + cartBooks.get(i).getPrice() + " USD");
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void updateAll(List<Book> books) {
        String[] list = new String[books.size()];

        for (int i = 0;i<books.size();i++) {
            list[i] = books.get(i).getTitle()+"\n"+ books.get(i).getPub_year()+ "\nPrice: " + books.get(i).getPrice() + " USD";
        }
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        resultListView.setAdapter(adapter);

        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3)
            {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Add to cart?");
                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.addToCart(position);
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

}
