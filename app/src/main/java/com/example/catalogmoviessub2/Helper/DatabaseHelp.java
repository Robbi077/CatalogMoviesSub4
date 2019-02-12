package com.example.catalogmoviessub2.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_DESCRIPTION;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_IMAGES;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_POSTER;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_RELEASE_DATE;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_TITLE;


public class DatabaseHelp extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbFavorite";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAV,
            _ID,
            FIELD_TITLE,
            FIELD_DESCRIPTION,
            FIELD_RELEASE_DATE,
            FIELD_IMAGES,
            FIELD_POSTER
    );

    public DatabaseHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV);
        onCreate(db);
    }
}
