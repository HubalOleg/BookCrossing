package com.oleg.hubal.bookcrossing.presentation.view.library;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oleg.hubal.bookcrossing.data.model.BookModel;

@StateStrategyType(SkipStrategy.class)
public interface LibraryView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void AddBook(BookModel bookModel);
}
