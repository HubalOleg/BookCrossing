package com.oleg.hubal.bookcrossing.ui.fragment.add_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.presentation.presenter.add_book.AddBookPresenter;
import com.oleg.hubal.bookcrossing.presentation.view.add_book.AddBookView;
import com.oleg.hubal.bookcrossing.ui.activity.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBookFragment extends MvpAppCompatFragment implements AddBookView {

    public static final String TAG = "AddBookFragment";
    public static int REQUEST_CODE_PICK_IMAGE = 1023;

    private MainActivity mMainActivity;

    private Uri mPhotoUri;

    @BindView(R.id.iv_book_photo)
    ImageView mPhotoImageView;
    @BindView(R.id.et_name_AB)
    EditText mNameEditText;
    @BindView(R.id.et_author_AB)
    EditText mAuthorEditText;
    @BindView(R.id.et_year_AB)
    EditText mYearEditText;

    @InjectPresenter
    AddBookPresenter mAddBookPresenter;
    private MaterialDialog mProgressDialog;

    public static AddBookFragment newInstance() {
        AddBookFragment fragment = new AddBookFragment();

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
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        ButterKnife.bind(AddBookFragment.this, view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE
                && resultCode == Activity.RESULT_OK
                && data != null
                && data.getData() != null) {

            mPhotoUri = data.getData();
            Glide.with(AddBookFragment.this).load(mPhotoUri).into(mPhotoImageView);
        }
    }

    @OnClick(R.id.ibtn_pick_photo)
    public void onPickPhotoClick() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @OnClick(R.id.btn_add_book_AB)
    public void onAddBookClick() {
        String name = mNameEditText.getText().toString();
        String author = mAuthorEditText.getText().toString();
        String year = mYearEditText.getText().toString();

        mAddBookPresenter.onAddBook(mPhotoUri, name, author, year);
    }

    @Override
    public void showChoosePhoto() {
        Toast.makeText(getContext(), R.string.error_choose_book_photo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyName() {
        mNameEditText.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void showEmptyAuthor() {
        mAuthorEditText.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void showEmptyYear() {
        mYearEditText.setError(getString(R.string.error_empty_field));
    }

    @Override
    public void successAddBook() {
        mMainActivity.onBackPressed();
    }

    @Override
    public void errorAddBook() {
        Toast.makeText(getContext(), R.string.error_server_internal, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.dialog_upload)
                .content(R.string.dialog_wait)
                .canceledOnTouchOutside(false)
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
