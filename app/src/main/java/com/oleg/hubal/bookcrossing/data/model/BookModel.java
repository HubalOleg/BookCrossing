package com.oleg.hubal.bookcrossing.data.model;

public class BookModel {

    private String mId;
    private String mName;
    private String mAuthor;
    private String mYear;
    private String mImageUrl;

    public BookModel() {
    }

    public BookModel(String id, String name, String author, String year) {
        mId = id;
        mName = name;
        mAuthor = author;
        mYear = year;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getName() {
        return mName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getYear() {
        return mYear;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
