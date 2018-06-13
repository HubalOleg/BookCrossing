package com.oleg.hubal.bookcrossing.presentation.view.add_book;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface AddBookView extends MvpView {

    void showChoosePhoto();
    void showEmptyName();
    void showEmptyAuthor();
    void showEmptyYear();
    void successAddBook();
    void errorAddBook();
    void showProgressDialog();
    void hideProgressDialog();

}
