package com.oleg.hubal.bookcrossing.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.main.MainPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.main.MainView;
import com.oleg.hubal.bookcrossing.ui.fragment.library.LibraryFragment;
import com.oleg.hubal.bookcrossing.ui.fragment.profile.ProfileFragment;
import com.oleg.hubal.bookcrossing.ui.fragment.search.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    @BindView(R.id.bnv_navigation_main)
    BottomNavigationView mBottomNavigationView;

    @InjectPresenter
    MainPresenter mMainPresenter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_library:
                    openFragment(LibraryFragment.newInstance(), false);
                    return true;
                case R.id.action_search:
                    openFragment(SearchFragment.newInstance(), false);
                    return true;
                case R.id.action_profile:
                    openFragment(ProfileFragment.newInstance(), false);
                    return true;
            }
            return false;
        }
    };

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initUI();

        if (savedInstanceState == null) {
            openFragment(LibraryFragment.newInstance(), false);
        }
    }

    public void openFragment(MvpAppCompatFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_container_main, fragment);
        if (addToBackStack) fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void initUI() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
