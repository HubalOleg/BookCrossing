package com.oleg.hubal.bookcrossing.presentation.presenter.library;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oleg.hubal.bookcrossing.data.FirebaseConstants;
import com.oleg.hubal.bookcrossing.data.model.BookModel;
import com.oleg.hubal.bookcrossing.presentation.view.library.LibraryView;

@InjectViewState
public class LibraryPresenter extends MvpPresenter<LibraryView> {

    private static final String TAG = "LibraryPresenter";

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private StorageReference mImageStorageReference;

    private ChildEventListener mBookEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            getViewState().AddBook(dataSnapshot.getValue(BookModel.class));
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public LibraryPresenter() {
        mImageStorageReference = FirebaseStorage.getInstance().getReference().child(FirebaseConstants.IMAGE_REFERENCE);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();

        String userId = mFirebaseAuth.getCurrentUser().getUid();
        mDatabaseReference.child(FirebaseConstants.BOOK_REFERENCE).child(userId).addChildEventListener(mBookEventListener);
    }

}
