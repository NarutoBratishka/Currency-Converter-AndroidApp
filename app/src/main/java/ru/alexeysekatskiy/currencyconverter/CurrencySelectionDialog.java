package ru.alexeysekatskiy.currencyconverter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CurrencySelectionDialog extends AppCompatActivity {

    private RecyclerView currencyListView;
    private CurrencyAdapter adapter;
    private static CurrencySelectionDialog current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_dialog);

        currencyListView = findViewById(R.id.currency_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        currencyListView.setLayoutManager(layoutManager);

        currencyListView.setHasFixedSize(true);

        adapter = new CurrencyAdapter();
        currencyListView.setAdapter(adapter);

        current = this;
    }

    public static void exeFinish() {
        current.finish();
    }
}