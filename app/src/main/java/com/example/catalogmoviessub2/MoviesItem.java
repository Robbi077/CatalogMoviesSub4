package com.example.catalogmoviessub2;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.catalogmoviessub2.Helper.DatabaseContract;

import org.json.JSONObject;

import static com.example.catalogmoviessub2.Helper.DatabaseContract.getColumnInt;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.getColumnString;

public class MoviesItem implements Parcelable {
    public static final Creator<MoviesItem> CREATOR = new Creator<MoviesItem>() {
        @Override
        public MoviesItem createFromParcel(Parcel source) {
            return new MoviesItem(source);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };
    private int id;
    private String title;
    private String overview;
    private String realease;
    private String images;
    private String poster;
    private String vote_average;

    public MoviesItem(Cursor i, int i1) {
        this.id = getColumnInt(i, DatabaseContract.FvColums.FIELD_ID);
        this.title = getColumnString(i, DatabaseContract.FvColums.FIELD_TITLE);
        this.overview = getColumnString(i, DatabaseContract.FvColums.FIELD_DESCRIPTION);
        this.realease = getColumnString(i, DatabaseContract.FvColums.FIELD_RELEASE_DATE);
        this.images = getColumnString(i, DatabaseContract.FvColums.FIELD_IMAGES);
        this.poster = getColumnString(i, DatabaseContract.FvColums.FIELD_POSTER);
    }

    public MoviesItem(JSONObject object) {
        try {
            this.title = object.getString("title");
            this.overview = object.getString("overview");
            this.realease = object.getString("release_date");
            this.images = object.getString("backdrop_path");
            this.poster = object.getString("poster_path");
            this.vote_average = object.getString("vote_average");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoviesItem() {
    }

    protected MoviesItem(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.realease = in.readString();
        this.images = in.readString();
        this.poster = in.readString();
        this.vote_average = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRealease() {
        return realease;
    }

    public void setRealease(String realease) {
        this.realease = realease;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.realease);
        dest.writeString(this.images);
        dest.writeString(this.poster);
        dest.writeString(this.vote_average);
    }

    public Object getRealeaseDate() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
