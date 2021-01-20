package ru.alexeysekatskiy.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
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

    private void getData() {
        NetworkService.getInstance()
            .getXmlApi()
            .getGeneralPost()
            .enqueue(new Callback<Post>() {
                @Override
                public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                    Post post = response.body();
                    if (response.isSuccessful()) {
                        Log.e("MAIN-INFO", post.getElement().get(0).getName());
                        Log.e("MAIN-INFO", post.getElement().get(1).getName());
                        Toast.makeText(MainActivity.this, post.getElement().get(0).getName(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.e("Main-EXCEPTION", t.getMessage());
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"katorabian@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Код ошибки");
                    intent.putExtra(Intent.EXTRA_TEXT, t.getMessage() + "\n" + Arrays.toString(t.getStackTrace()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
    }
}