package com.oleg.hubal.bookcrossing.ui.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.auth.AuthPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.auth.AuthView;
import com.oleg.hubal.bookcrossing.ui.fragment.login.LoginFragment;

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

        if (savedInstanceState == null) {
            openFragment(LoginFragment.newInstance(), false);
        }
    }

    public void openFragment(MvpAppCompatFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cv_container_auth, fragment);
        if (addToBackStack) fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
