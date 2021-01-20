package ru.alexeysekatskiy.currencyconverter;

import android.support.annotation.NonNull;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Iterator;

public class CurrencyList implements Iterable<CurrencyBucket> {
    static volatile int size = 0;
    int iteratorIteration = 0;
    static CurrencyBucket[] valute = new CurrencyBucket[16];



    static void add(CurrencyBucket bucket) {
        if (size >= valute.length - 1) {
            CurrencyBucket[] newContainer;

            newContainer = copyFromPreviousBucketArray(valute);
            valute = newContainer;
        }
        valute[size++] = bucket;
    }

    private static CurrencyBucket[] copyFromPreviousBucketArray(CurrencyBucket[] valute) {
        CurrencyBucket[] newContainer = new CurrencyBucket[size * 2 + 1];
        int inc = 0;
        for (CurrencyBucket bucket: valute) {
            newContainer[inc++] = bucket;
        }

        return newContainer;
    }


    static CurrencyBucket get(int index) {
        return valute[index];
    }


    static CurrencyBucket get(String charCode) {

        for (CurrencyBucket bucket : valute) {
            if (bucket.getCharCode().equals(charCode))
                return bucket;
        }
        return null;
    }

    static void clear() {

        for (CurrencyBucket bucket: valute) {
            bucket = null;
        }

        size = 0;
        valute = new CurrencyBucket[16];
    }

    @NonNull
    @Override
    public Iterator<CurrencyBucket> iterator() {
        return new Iterator<CurrencyBucket>() {

            @Override
            public boolean hasNext() {
                return iteratorIteration <= size;
            }

            @Override
            public CurrencyBucket next() {
                return CurrencyList.get(iteratorIteration++);
            }
        };
    }
}
