package com.oleg.hubal.bookcrossing.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static final String KEY_EMAIL = "KEY_EMAIL";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";

    private SharedPreferences mSharedPreferences;

    public AppPrefs(Context context) {
        mSharedPreferences = context.getSharedPreferences("BookCrossing", Context.MODE_PRIVATE);
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString(KEY_EMAIL, "");
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return mSharedPreferences.getString(KEY_PASSWORD, "");
    }
}
