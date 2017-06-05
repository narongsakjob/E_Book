package com.example.job.e_book.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.job.e_book.R;

import java.util.ArrayList;


public class UserActivity extends AppCompatActivity implements UserView {
    ListView resultListView;
    TextView cash;
    UserPresenter presenter = null;
    Button buyBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        initViewHolders();

        if(presenter == null) {
            presenter = new UserPresenter(this,getIntent());
        }

    }

    private void initViewHolders() {
        resultListView = (ListView) findViewById(R.id.listProfile);
        cash = (TextView) findViewById(R.id.cash);
        buyBt = (Button) findViewById(R.id.purchaseButton);
    }
    public void onClickAddMoney(View view) {
        EditText editText = (EditText)findViewById(R.id.refillText);
        double amount = Double.parseDouble(editText.getText().toString());
        presenter.refill(amount);
    }
    public void onClickBack(View view) {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);

        ArrayList<String> collection = presenter.user.checkCollection();

        intent.putExtra("collectionSize", collection.size()+"");
        intent.putExtra("cash", presenter.getCash()+"");
        for (int i = 0;i<collection.size();i++) {
            intent.putExtra("collection"+i,collection.get(i));
        }
        startActivity(intent);
        finish();
    }

    public void onClickCart(View view) {
        presenter.showCart();
        buyBt.setVisibility(View.VISIBLE);
    }

    public void onClickHistory(View view) {
        presenter.showCollection();
        buyBt.setVisibility(View.INVISIBLE);
    }

    public void onPurchase(View view) {
        presenter.purchase();
    }

    @Override
    public void updateCash(double amount) {
        cash.setText("Money: "+ amount + " USD");
    }

    @Override
    public void updateAll(ArrayList<String> books) {
        String[] list = new String[books.size()];

        for (int i = 0;i<books.size();i++) {
            list[i] = books.get(i);
        }
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        resultListView.setAdapter(adapter);

    }

}
