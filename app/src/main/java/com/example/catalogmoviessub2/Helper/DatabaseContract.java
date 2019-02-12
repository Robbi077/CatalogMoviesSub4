package com.example.catalogmoviessub2.Helper;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.example.catalogmoviessub2.";
    public static String TABLE_FAV = "favorite";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAV)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static final class FvColums implements BaseColumns {
        //Note Id
        public static String FIELD_ID = "_ID";
        //Note title
        public static String FIELD_TITLE = "title";
        //Note description
        public static String FIELD_DESCRIPTION = "overview";
        //Note date
        public static String FIELD_RELEASE_DATE = "release_date";
        //Note date
        public static String FIELD_IMAGES = "backdrop_path";
        public static String FIELD_POSTER = "poster_path";
    }
}

