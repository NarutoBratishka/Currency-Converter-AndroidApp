package ru.alexeysekatskiy.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.simpleframework.xml.Path;

import java.util.List;

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
//                .getAllPost()
//                .enqueue(new Callback<List<Post>>() {
//                    @Override
//                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                        if (response.isSuccessful()) {
//                            Log.e("Main-INFO-SUCCES", String.valueOf(response.body().size()));
//                            List<Post> postList = response.body();
//                            for (Post post : postList) {
//                                Log.e("Main-INFO", post.getName());
//                                Log.e("Main-INFO", post.getValue());
//                            }
//                        } else {
//                            Log.e("Main-INFO-DENIED", String.valueOf(response.code()));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Post>> call, Throwable t) {
//                        Log.e("Main-EXCEPTION", t.getMessage());
//                    }
//                });

                .getGeneralPost()
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(@NonNull Call<Post> call,@NonNull Response<Post> response) {
                        Post post = response.body();
                        Log.e("Main-INFO", post.getName());
                        Log.e("Main-INFO", post.getValue());
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("Main-EXCEPTION", t.getMessage());
                    }
                });
    }
}