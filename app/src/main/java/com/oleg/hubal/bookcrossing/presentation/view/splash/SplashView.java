package com.oleg.hubal.bookcrossing.presentation.view.splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface SplashView extends MvpView {

    void StartAuthActivity();

    void StartMainActivity();
}
