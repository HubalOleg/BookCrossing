package com.oleg.hubal.bookcrossing.ui.fragment.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.library.LibraryPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.library.LibraryView;

public class LibraryFragment extends MvpAppCompatFragment implements LibraryView {
    public static final String TAG = "LibraryFragment";
    @InjectPresenter
    LibraryPresenter mLibraryPresenter;

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
