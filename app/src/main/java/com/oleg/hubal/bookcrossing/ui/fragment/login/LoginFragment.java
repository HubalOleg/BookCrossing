package com.oleg.hubal.bookcrossing.ui.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.data.AppPrefs;
import com.oleg.hubal.bookcrossing.presentation.presenter.login.LoginPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.login.LoginView;
import com.oleg.hubal.bookcrossing.ui.activity.auth.AuthActivity;
import com.oleg.hubal.bookcrossing.ui.activity.main.MainActivity;
import com.oleg.hubal.bookcrossing.ui.fragment.signup.SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends MvpAppCompatFragment implements LoginView {
    public static final String TAG = "LoginFragment";

    private AuthActivity mAuthActivity;

    @BindView(R.id.et_email_login)
    EditText mEmailEditText;
    @BindView(R.id.et_password_login)
    EditText mPasswordEditText;

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @ProvidePresenter
    public LoginPresenter ProvideLoginPresenter() {
        return new LoginPresenter(new AppPrefs(getContext()));
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mAuthActivity = (AuthActivity) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "Context should be an instance of AuthActivity");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(LoginFragment.this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @OnClick(R.id.btn_sign_in_login)
    public void onSignInClick() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mLoginPresenter.onSignIn(email, password);
    }

    @OnClick(R.id.btn_sign_up_login)
    public void onSignUpClick() {
        mAuthActivity.openFragment(SignUpFragment.newInstance(), true);
    }

    @Override
    public void ShowInvalidEmail() {
        mEmailEditText.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void ShowInvalidPassword() {
        mPasswordEditText.setError(getString(R.string.error_invalid_password));
    }

    @Override
    public void OpenMainActivity() {
        startActivity(MainActivity.getIntent(getContext()));
    }

    @Override
    public void ShowAuthorizationError() {
        Toast.makeText(getContext(), R.string.error_authentication, Toast.LENGTH_SHORT).show();
    }
}
