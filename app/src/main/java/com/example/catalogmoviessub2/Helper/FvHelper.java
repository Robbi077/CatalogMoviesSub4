package com.example.catalogmoviessub2.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.catalogmoviessub2.MoviesItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_DESCRIPTION;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_IMAGES;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_POSTER;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_RELEASE_DATE;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.FvColums.FIELD_TITLE;
import static com.example.catalogmoviessub2.Helper.DatabaseContract.TABLE_FAV;

public class FvHelper {
    private Context context;
    private DatabaseHelp dataBaseHelper;
    private SQLiteDatabase database;

    public FvHelper(Context context) {
        this.context = context;
    }

    public FvHelper Open() throws SQLException {
        dataBaseHelper = new DatabaseHelp(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<MoviesItem> query() {
        ArrayList<MoviesItem> arrayList = new ArrayList<MoviesItem>();
        Cursor cursor = database.query(TABLE_FAV, null, null,
                null, null, null, _ID + " DESC",
                null);
        cursor.moveToFirst();
        MoviesItem moviesItems;
        if (cursor.getCount() > 0) {
            do {
                moviesItems = new MoviesItem();
                moviesItems.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_TITLE)));
                moviesItems.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_DESCRIPTION)));
                moviesItems.setRealease(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_RELEASE_DATE)));
                moviesItems.setImages(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_IMAGES)));
                moviesItems.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(FIELD_POSTER)));
                arrayList.add(moviesItems);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(MoviesItem moviesItems) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(FIELD_TITLE, moviesItems.getTitle());
        initialValues.put(FIELD_DESCRIPTION, moviesItems.getOverview());
        initialValues.put(FIELD_RELEASE_DATE, moviesItems.getRealease());
        initialValues.put(FIELD_IMAGES, moviesItems.getImages());
        initialValues.put(FIELD_POSTER, moviesItems.getPoster());
        return database.insert(TABLE_FAV, null, initialValues);
    }

    public int update(MoviesItem moviesItems) {
        ContentValues args = new ContentValues();
        args.put(FIELD_TITLE, moviesItems.getTitle());
        args.put(FIELD_DESCRIPTION, moviesItems.getOverview());
        args.put(FIELD_RELEASE_DATE, moviesItems.getRealease());
        args.put(FIELD_IMAGES, moviesItems.getImages());
        args.put(FIELD_POSTER, moviesItems.getPoster());
        return database.update(TABLE_FAV, args, _ID
                + "= '" + moviesItems.getId() + "'", null);
    }

    public int delete(String title) {
        return database.delete(TABLE_FAV, FIELD_TITLE + " = '" + title + "'", null);
    }

    public boolean isFavorit(String title) {

        String query = String.format("SELECT * FROM " + TABLE_FAV + " WHERE title = '%s'", title);
        Log.d("tgquery", query);
        Cursor cursor = database.rawQuery(query, null);
        Log.d("tgquery", query);
        if (cursor.getCount() > 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(TABLE_FAV, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(TABLE_FAV
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(TABLE_FAV, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(TABLE_FAV, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(TABLE_FAV, _ID + " = ?", new String[]{id});
    }

}
