package ru.alexeysekatskiy.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static boolean rightSide = false;
    static CurrencyBucket leftValute;
    static CurrencyBucket rightValute;
    EditText leftEdit;
    EditText rightEdit;
    Button leftBtn;
    Button rightBtn;
    InputMethodManager manager;
    volatile int listLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();

        leftBtn = findViewById(R.id.button_val_left);
        rightBtn = findViewById(R.id.button_val_right);
        leftEdit = (EditText) findViewById(R.id.value_left);
        rightEdit = (EditText) findViewById(R.id.value_right);

    }

    @SuppressLint("DefaultLocale")
    public void calculateValute() {
        if (!rightSide) {
            double leftEditDigit = Double.parseDouble(String.valueOf(leftEdit.getText()));
            double leftSum = leftValute.getValue() *
                    leftEditDigit;

            double result = 0d;

            try {
                result = leftSum / rightValute.getValue();
            } catch (ArithmeticException e) {
                result = 0.0;
            }

            rightEdit.setText(String.format("%.2f", result).replace(',', '.'));
            leftEdit.setText(String.format("%.2f", leftEditDigit).replace(',', '.'));
        } else {
            double rightEditDigit = Double.parseDouble(String.valueOf(rightEdit.getText()));
            double rightSum = rightValute.getValue() *
                    rightEditDigit;

            double result = 0d;

            try {
                result = rightSum / leftValute.getValue();
            } catch (ArithmeticException e) {
                result = 0.0;
            }

            leftEdit.setText(String.format("%.2f", result).replace(',', '.'));
            rightEdit.setText(String.format("%.2f", rightEditDigit).replace(',', '.'));
        }
    }



    public void changeCurrencyRight(View view) {
        rightSide = true;
        Intent intent = new Intent(MainActivity.this, CurrencySelectionDialog.class);
        startActivity(intent);
    }

    public void changeCurrencyLeft(View view) {
        rightSide = false;
        Intent intent = new Intent(MainActivity.this, CurrencySelectionDialog.class);
        startActivity(intent);
    }

    public void swap(View view) {
        Toast.makeText(getApplicationContext(), "Не реализовано", Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        calculateValute();  ///////
    }

    public static boolean isRightActivity() {
        return rightSide;
    }

    private void getData() {
        NetworkService.getInstance()
            .getXmlApi()
            .getGeneralPost()
            .enqueue(new Callback<Post>() {
                @Override
                public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                    Post post = response.body();
                    if (response.isSuccessful()) {
                        listLength = post.getElement().size();
                        fillCurrencyList(post);
//                        Toast.makeText(MainActivity.this, post.getElement().get(0).getName(), Toast.LENGTH_LONG).show(); /////

                        leftValute = CurrencyList.get("USD");
                        rightValute = CurrencyList.get("RUB");
                        calculateValute();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    sendErrorMessage(t);
                }
            });
    }


    private void fillCurrencyList(Post post) {
        CurrencyList.clear();

        String charCode;
        double resultValue;
        double nominal;
        double value;
        String name;

        CurrencyList.add(new CurrencyBucket("RUB", 1, "Российский рубль"));
        for (PostElement element : post.getElement()) {
            charCode = element.getCharCode();
            nominal = Double.parseDouble(element.getNominal().replace(',', '.'));
            value = Double.parseDouble(element.getValue().replace(',', '.'));
            resultValue = value/nominal;
            name = element.getName();

            CurrencyList.add(new CurrencyBucket(charCode, resultValue, name));
        }
    }


    private void sendErrorMessage(Throwable t) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"katorabian@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Код ошибки");
        intent.putExtra(Intent.EXTRA_TEXT, t.getMessage() + "\n" + Arrays.toString(t.getStackTrace()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}