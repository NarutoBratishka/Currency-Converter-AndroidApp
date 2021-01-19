package ru.alexeysekatskiy.currencyconverter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public class TempPostArray extends ArrayList<Post> {
    Post innerPost;

    public TempPostArray(int initialCapacity, Post innerPost) {
        super(initialCapacity);
        this.innerPost = innerPost;
    }

    public TempPostArray(Post innerPost) {
        this.innerPost = innerPost;
    }

    public TempPostArray(@NonNull Collection<? extends Post> c, Post innerPost) {
        super(c);
        this.innerPost = innerPost;
    }
}
