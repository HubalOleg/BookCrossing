package com.oleg.hubal.bookcrossing.ui.fragment.library;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.adapter.BookAdapter;
import com.oleg.hubal.bookcrossing.data.model.BookModel;
import com.oleg.hubal.bookcrossing.presentation.presenter.library.LibraryPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.library.LibraryView;
import com.oleg.hubal.bookcrossing.ui.activity.main.MainActivity;
import com.oleg.hubal.bookcrossing.ui.fragment.add_book.AddBookFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LibraryFragment extends MvpAppCompatFragment implements LibraryView {
    public static final String TAG = "LibraryFragment";

    private MainActivity mMainActivity;

    private LinearLayoutManager mLinearLayoutManager;
    private BookAdapter mBookAdapter;

    @BindView(R.id.rv_book_container)
    RecyclerView mBookRecyclerView;

    @InjectPresenter
    LibraryPresenter mLibraryPresenter;

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mMainActivity = (MainActivity) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "Context should be an instance of MainActivity");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        ButterKnife.bind(LibraryFragment.this, view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBookRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mBookRecyclerView.setLayoutManager(mLinearLayoutManager);

        mBookAdapter = new BookAdapter(getContext());
        mBookRecyclerView.setAdapter(mBookAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.fab_create_book)
    public void onCreateBookClick() {
        mMainActivity.openFragment(AddBookFragment.newInstance(), true);
    }

    @Override
    public void AddBook(BookModel bookModel) {
        mBookAdapter.addBook(bookModel);
    }
}
