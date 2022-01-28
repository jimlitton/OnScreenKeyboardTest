package com.nvoq.onscreenkeyboardtest;

import android.content.ComponentName;
import android.inputmethodservice.InputMethodService;;

public class MyKeyboard extends InputMethodService {

    protected String getSettingsInputMethodId() {
        return new ComponentName(getApplication(), MyKeyboard.class).flattenToShortString();
    }
}

