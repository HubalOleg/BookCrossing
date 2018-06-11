package com.oleg.hubal.bookcrossing.ui.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.search.SearchPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.search.SearchView;

public class SearchFragment extends MvpAppCompatFragment implements SearchView {
    public static final String TAG = "SearchFragment";
    @InjectPresenter
    SearchPresenter mSearchPresenter;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
