package com.oleg.hubal.bookcrossing.presentation.view.signup;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface SignUpView extends MvpView {
    void ShowInvalidEmail();
    void ShowInvalidPassword();
    void ShowSignInScreen();
    void ShowRegistrationError();

}
