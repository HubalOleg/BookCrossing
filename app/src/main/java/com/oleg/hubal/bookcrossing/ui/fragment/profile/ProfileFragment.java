package com.oleg.hubal.bookcrossing.ui.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.profile.ProfilePresenter;
import com.oleg.hubal.bookcrossing.presentation.view.profile.ProfileView;
import com.oleg.hubal.bookcrossing.ui.activity.auth.AuthActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {
    public static final String TAG = "ProfileFragment";
    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(ProfileFragment.this, view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.btn_logout)
    public void OnLogoutClick() {
        FirebaseAuth.getInstance().signOut();
        startActivity(AuthActivity.getIntent(getContext()));
    }
}
