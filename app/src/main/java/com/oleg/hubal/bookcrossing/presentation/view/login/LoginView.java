package com.oleg.hubal.bookcrossing.presentation.view.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface LoginView extends MvpView {
    void ShowInvalidEmail();
    void ShowInvalidPassword();
    void OpenMainActivity();
    void ShowAuthorizationError();
}
