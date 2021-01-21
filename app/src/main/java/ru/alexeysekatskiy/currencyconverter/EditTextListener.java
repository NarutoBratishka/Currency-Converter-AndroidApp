package ru.alexeysekatskiy.currencyconverter;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;

public class EditTextListener implements TextWatcher {
    private String _before;
    private String _old;
    private String _new;
    private String _after;
    private boolean _ignore = false;


    @Override
    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        _before = sequence.subSequence(0,start).toString();
        _old = sequence.subSequence(start, start+count).toString();
        _after = sequence.subSequence(start+count, sequence.length()).toString();
    }

    @Override
    public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        _new = sequence.subSequence(start, start+count).toString();
    }

    @Override
    public void afterTextChanged(Editable sequence) {
        if (_ignore)
            return;

        onTextChanged(_before, _old, _new, _after);
    }

    @SuppressLint("DefaultLocale")
    protected void onTextChanged(String before, String old, String aNew, String after) {
        after = String.format("%.2f", Double.parseDouble(after));
    }

    protected void startUpdates(){
        _ignore = true;
    }

    protected void endUpdates(){
        _ignore = false;
    }
}
