package ru.alexeysekatskiy.currencyconverter;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private final static String BASE_URL = "https://www.cbr.ru";
    private Retrofit mRetrofit;


    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.
                        createNonStrict(new Persister(new AnnotationStrategy())))
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public CurrencyPlaceHolderApi getXmlApi() {
        return mRetrofit.create(CurrencyPlaceHolderApi.class);
    }
}
