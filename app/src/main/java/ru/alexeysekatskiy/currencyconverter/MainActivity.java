package ru.alexeysekatskiy.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static boolean rightSide = false;
    static CurrencyBucket firstValute;
    static CurrencyBucket secondValute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeCurrencyRight(View view) {
        Toast toast =  Toast.makeText(getApplicationContext(),
                            "Не реализовано", Toast.LENGTH_SHORT);
                    toast.show();
    }

    public void changeCurrencyLeft(View view) {
        Toast toast =  Toast.makeText(getApplicationContext(),
                "Не реализовано", Toast.LENGTH_SHORT);
        toast.show();
    }

    public static boolean isRightActivity() {
        return rightSide;
    }
}