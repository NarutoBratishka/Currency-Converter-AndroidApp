package ru.alexeysekatskiy.currencyconverter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static boolean rightSide = false;
    static CurrencyBucket firstValute;
    static CurrencyBucket secondValute;
    EditText firstEdit;
    EditText secondEdit;
    TextView firstTextView;
    TextView secondTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

        }


    }

    public void changeCurrencyRight(View view) {
        AsyncTask.execute(new XMLParser());
        Intent intent = new Intent(MainActivity.this, CurrencySelectionDialog.class);
        startActivity(intent);
    }

    public void changeCurrencyLeft(View view) {
        Toast.makeText(getApplicationContext(), "Не реализовано", Toast.LENGTH_SHORT).show();
    }

    public void swap(View view) {
        Toast.makeText(getApplicationContext(), "Не реализовано", Toast.LENGTH_SHORT).show();
    }

    public static boolean isRightActivity() {
        return rightSide;
    }
}