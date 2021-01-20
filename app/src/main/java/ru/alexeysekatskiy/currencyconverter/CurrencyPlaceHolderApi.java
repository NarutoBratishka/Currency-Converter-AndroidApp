package ru.alexeysekatskiy.currencyconverter;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyPlaceHolderApi {
    @GET("/scripts/XML_daily.asp")
    public Call<Post> getGeneralPost();

}
