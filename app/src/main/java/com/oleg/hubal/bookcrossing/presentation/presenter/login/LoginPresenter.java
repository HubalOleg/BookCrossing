package com.oleg.hubal.bookcrossing.presentation.presenter.login;


import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oleg.hubal.bookcrossing.data.AppPrefs;
import com.oleg.hubal.bookcrossing.helpers.InputUtils;
import com.oleg.hubal.bookcrossing.presentation.view.login.LoginView;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    private AppPrefs mAppPrefs;
    private FirebaseAuth mAuth;

    private String mLastEmail;
    private String mLastPassword;

    private OnCompleteListener<AuthResult> mOnSignInCompleteListener = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                mAppPrefs.setEmail(mLastEmail);
                mAppPrefs.setPassword(mLastPassword);
                getViewState().OpenMainActivity();
            } else {
                getViewState().ShowAuthorizationError();
            }
        }
    };

    public LoginPresenter(AppPrefs appPrefs) {
        mAppPrefs = appPrefs;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mAuth = FirebaseAuth.getInstance();
    }

    public void onSignIn(String email, String password) {
        mLastEmail = email;
        mLastPassword = password;

        if (isInputValid(email, password)) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mOnSignInCompleteListener);
        }
    }

    private boolean isInputValid(String email, String password) {
        boolean isValid = true;

        if (!InputUtils.isEmailValid(email)) {
            getViewState().ShowInvalidEmail();
            isValid = false;
        }

        if (!InputUtils.isPasswordValid(password)) {
            getViewState().ShowInvalidPassword();
            isValid = false;
        }
        return isValid;
    }
}
