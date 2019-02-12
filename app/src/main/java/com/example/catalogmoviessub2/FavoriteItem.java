package com.example.catalogmoviessub2;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.catalogmoviessub2.Helper.DatabaseContract;

import static com.example.catalogmoviessub2.Helper.DatabaseContract.getColumnString;

public class FavoriteItem implements Parcelable {
    public static final Creator<FavoriteItem> CREATOR = new Creator<FavoriteItem>() {
        @Override
        public FavoriteItem createFromParcel(Parcel in) {
            return new FavoriteItem(in);
        }

        @Override
        public FavoriteItem[] newArray(int size) {
            return new FavoriteItem[size];
        }
    };
    private int id;
    private String title;
    private String Detail;
    private String vote_average;
    private String Images;
    private String realeaseDate;
    private String Poster;

    public FavoriteItem() {
    }

    public FavoriteItem(Cursor cursor) {
        this.title = getColumnString(cursor, DatabaseContract.FvColums.FIELD_TITLE);
        this.Detail = getColumnString(cursor, DatabaseContract.FvColums.FIELD_DESCRIPTION);
        this.realeaseDate = getColumnString(cursor, DatabaseContract.FvColums.FIELD_RELEASE_DATE);
        this.Images = getColumnString(cursor, DatabaseContract.FvColums.FIELD_IMAGES);
        this.Poster = getColumnString(cursor, DatabaseContract.FvColums.FIELD_POSTER);
    }

    protected FavoriteItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.Detail = in.readString();
        this.vote_average = in.readString();
        this.Images = in.readString();
        this.realeaseDate = in.readString();
        this.Poster = in.readString();
    }

    public static Creator<FavoriteItem> getCREATOR() {
        return CREATOR;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(String realeaseDate) {
        this.realeaseDate = realeaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.Detail);
        dest.writeString(this.vote_average);
        dest.writeString(this.Images);
        dest.writeString(this.realeaseDate);
        dest.writeString(this.Poster);
    }
}
