package ru.alexeysekatskiy.currencyconverter;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private final static String BASE_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private Retrofit mRetrofit;


    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }
}
