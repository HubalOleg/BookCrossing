package com.oleg.hubal.bookcrossing.presentation.presenter.add_book;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oleg.hubal.bookcrossing.data.FirebaseConstants;
import com.oleg.hubal.bookcrossing.data.model.BookModel;
import com.oleg.hubal.bookcrossing.presentation.view.add_book.AddBookView;

import java.util.UUID;

@InjectViewState
public class AddBookPresenter extends MvpPresenter<AddBookView> {

    private final DatabaseReference mDatabaseReference;

    private final FirebaseAuth mFirebaseAuth;
    private final StorageReference mImageStorageReference;

    private BookModel mBookModel;

    private OnSuccessListener<UploadTask.TaskSnapshot> mOnImageUploadSuccessListener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            String userId = mFirebaseAuth.getCurrentUser().getUid();

            mDatabaseReference.child(FirebaseConstants.BOOK_REFERENCE).child(userId).child(mBookModel.getId()).setValue(mBookModel);
            getViewState().hideProgressDialog();
            getViewState().successAddBook();
        }
    };

    private OnFailureListener mOnImageUploadFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            getViewState().hideProgressDialog();
            getViewState().errorAddBook();
        }
    };

    public AddBookPresenter() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mImageStorageReference = FirebaseStorage.getInstance().getReference().child(FirebaseConstants.IMAGE_REFERENCE);
    }

    public void onAddBook(Uri photoUri, String name, String author, String year) {
        if (isInputValid(photoUri, name, author, year)) {
            getViewState().showProgressDialog();

            String bookId = UUID.randomUUID().toString();
            mBookModel = new BookModel(bookId, name, author, year);

            uploadBookPhoto(photoUri);
        }
    }

    private void uploadBookPhoto(Uri photoUri) {
        mImageStorageReference.child(mBookModel.getId()).putFile(photoUri)
                .addOnSuccessListener(mOnImageUploadSuccessListener)
                .addOnFailureListener(mOnImageUploadFailureListener);
    }

    private boolean isInputValid(Uri photoUri, String name, String author, String year) {
        boolean isValid = true;

        if (photoUri == null) {
            getViewState().showChoosePhoto();
            isValid = false;
        }

        if (TextUtils.isEmpty(name)) {
            getViewState().showEmptyName();
            isValid = false;
        }

        if (TextUtils.isEmpty(author)) {
            getViewState().showEmptyAuthor();
            isValid = false;
        }

        if (TextUtils.isEmpty(year)) {
            getViewState().showEmptyYear();
            isValid = false;
        }
        return isValid;
    }

}
