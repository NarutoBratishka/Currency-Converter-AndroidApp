package ru.alexeysekatskiy.currencyconverter;

import org.simpleframework.xml.Path;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyPlaceHolderApi {
    @GET("/scripts/XML_daily.asp")
    public Call<Post> getGeneralPost();

    @GET("/scripts/XML_daily.asp")
    public Call<List<Post>> getAllPost();

}
