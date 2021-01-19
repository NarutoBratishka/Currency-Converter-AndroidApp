package ru.alexeysekatskiy.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static boolean rightSide = false;
    static CurrencyBucket leftValute;
    static CurrencyBucket rightValute;
    EditText leftEdit;
    EditText rightEdit;
    Button leftBtn;
    Button rightBtn;
    InputMethodManager manager;
    ProgressBar progressBar;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        mainLayout = findViewById(R.id.main_layout);/////

        mainLayout.setVisibility(View.GONE); /////
        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            public void run() {
                try{
                    String content = download("http://www.cbr.ru/scripts/XML_daily.asp");
                    progressBar.post(new Runnable() {
                        public void run() {
                            XMLParser parser = new XMLParser();
                            if(parser.parse(content)) {
                                mainLayout.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                catch (IOException ex){
                    Log.e("Main-download", Arrays.toString(ex.getStackTrace()));
                    mainLayout.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    public void changeCurrencyRight(View view) {
        rightSide = true;
        Intent intent = new Intent(MainActivity.this, CurrencySelectionDialog.class);
        startActivity(intent);
    }

    public void changeCurrencyLeft(View view) {
        Toast.makeText(getApplicationContext(), "Не реализовано", Toast.LENGTH_SHORT).show();
    }

    public void swap(View view) {
        Toast.makeText(getApplicationContext(), "Не реализовано", Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        calculateValute();
    }

    public static boolean isRightActivity() {
        return rightSide;
    }


    private String download(String urlPath) throws IOException {
        StringBuilder xmlResult = new StringBuilder();
        BufferedReader reader = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpsURLConnection) url.openConnection();
            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line=reader.readLine()) != null) {
                xmlResult.append(line);
            }
            return xmlResult.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}