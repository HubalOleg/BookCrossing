package com.oleg.hubal.bookcrossing.ui.fragment.signup;

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
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.signup.SignUpPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.signup.SignUpView;
import com.oleg.hubal.bookcrossing.ui.activity.auth.AuthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends MvpAppCompatFragment implements SignUpView {
    public static final String TAG = "SignUpFragment";

    private AuthActivity mAuthActivity;

    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    @BindView(R.id.et_email_sign_up)
    EditText mEmailEditText;
    @BindView(R.id.et_password_sign_up)
    EditText mPasswordEditText;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();

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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(SignUpFragment.this, view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.btn_register)
    public void OnRegisterClick() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mSignUpPresenter.OnSignUp(email, password);
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
    public void ShowSignInScreen() {
        mAuthActivity.onBackPressed();
    }

    @Override
    public void ShowRegistrationError() {
        Toast.makeText(getContext(), R.string.error_registration, Toast.LENGTH_SHORT).show();
    }
}
