package com.oleg.hubal.bookcrossing.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oleg.hubal.bookcrossing.R;
import com.oleg.hubal.bookcrossing.data.FirebaseConstants;
import com.oleg.hubal.bookcrossing.data.model.BookModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<BookModel> mBookModelList = new ArrayList<>();
    private final Context mContext;

    public BookAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_book_item, parent,false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.onBind(position, mBookModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBookModelList.size();
    }

    public void addBook(BookModel bookModel) {
        mBookModelList.add(bookModel);
        notifyItemInserted(mBookModelList.size() - 1);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_photo_CBI)
        ImageView mPhotoImageView;
        @BindView(R.id.tv_book_name_CBI)
        TextView mNameTextView;
        @BindView(R.id.tv_book_author_CBI)
        TextView mAuthorTextView;
        @BindView(R.id.tv_book_year_CBI)
        TextView mYearTextView;

        private int mPosition;
        private BookModel mBookModel;
        private final StorageReference mImageStorageReference;

        BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(BookViewHolder.this, itemView);
            mImageStorageReference = FirebaseStorage.getInstance().getReference().child(FirebaseConstants.IMAGE_REFERENCE);
        }

        void onBind(int position, BookModel bookModel) {
            mPosition = position;
            mBookModel = bookModel;

            mNameTextView.setText(bookModel.getName());
            mAuthorTextView.setText(bookModel.getAuthor());
            mYearTextView.setText(bookModel.getYear());

            Glide.with(mContext).load(mImageStorageReference.child(bookModel.getId())).into(mPhotoImageView);
        }
    }
}
