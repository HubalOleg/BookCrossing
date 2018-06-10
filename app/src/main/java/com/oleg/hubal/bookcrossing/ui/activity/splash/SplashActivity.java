package com.oleg.hubal.bookcrossing.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.data.AppPrefs;
import com.oleg.hubal.bookcrossing.presentation.presenter.splash.SplashPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.splash.SplashView;
import com.oleg.hubal.bookcrossing.ui.activity.auth.AuthActivity;
import com.oleg.hubal.bookcrossing.ui.activity.main.MainActivity;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    public static final String TAG = "SplashActivity";

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @ProvidePresenter
    public SplashPresenter ProvideSplashPresenter() {
        return new SplashPresenter(new AppPrefs(this));
    }

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SplashActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void StartAuthActivity() {
        startActivity(AuthActivity.getIntent(SplashActivity.this));
    }

    @Override
    public void StartMainActivity() {
        startActivity(MainActivity.getIntent(SplashActivity.this));
    }
}
