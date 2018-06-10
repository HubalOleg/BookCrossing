package com.oleg.hubal.bookcrossing.helpers;

import android.util.Patterns;

public class InputUtils {

    private final static int MINIMAL_PASSWORD_LENGTH = 6;

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= MINIMAL_PASSWORD_LENGTH;
    }
}
