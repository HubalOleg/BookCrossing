package com.oleg.hubal.bookcrossing.presentation.presenter.signup;


import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oleg.hubal.bookcrossing.helpers.InputUtils;
import com.oleg.hubal.bookcrossing.presentation.view.signup.SignUpView;

@InjectViewState
public class SignUpPresenter extends MvpPresenter<SignUpView> {

    private FirebaseAuth mAuth;

    private OnCompleteListener<AuthResult> mOnSignInCompleteListener = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                getViewState().ShowSignInScreen();
            } else {
                getViewState().ShowRegistrationError();
            }
        }
    };

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mAuth = FirebaseAuth.getInstance();
    }

    public void OnSignUp(String email, String password) {
        if (isInputValid(email, password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
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
