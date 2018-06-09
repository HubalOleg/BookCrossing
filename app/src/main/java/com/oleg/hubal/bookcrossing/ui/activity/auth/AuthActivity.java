package com.oleg.hubal.bookcrossing.ui.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.presentation.presenter.auth.AuthPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.auth.AuthView;

public class AuthActivity extends MvpAppCompatActivity implements AuthView {

    public static final String TAG = "AuthActivity";

    @InjectPresenter
    AuthPresenter mAuthPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, AuthActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.oleg.hubal.bookcrossing.R.layout.activity_auth);


    }
}
