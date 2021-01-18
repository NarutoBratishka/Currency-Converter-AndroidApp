package ru.alexeysekatskiy.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CurrencySelectionDialog extends AppCompatActivity {

    private RecyclerView currencyListView;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_dialog);


        currencyListView = findViewById(R.id.currency_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        currencyListView.setLayoutManager(layoutManager);

        currencyListView.setHasFixedSize(true);

        adapter = new CurrencyAdapter(/*this*/);
        currencyListView.setAdapter(adapter);
    }
}