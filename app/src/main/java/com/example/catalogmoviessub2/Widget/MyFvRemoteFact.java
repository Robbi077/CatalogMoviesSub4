package com.example.catalogmoviessub2.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.catalogmoviessub2.FavoriteItem;
import com.example.catalogmoviessub2.Helper.DatabaseContract;
import com.example.catalogmoviessub2.R;

import java.util.concurrent.ExecutionException;

/**
 * Implementation of App Widget functionality.
 */
public class MyFvRemoteFact implements RemoteViewsService.RemoteViewsFactory {

    Cursor cursor;
    private Context context;
    private int mAppWidgetId;

    public MyFvRemoteFact(Context mContext, Intent intent) {
        this.context = mContext;
        this.mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    private FavoriteItem getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavoriteItem(cursor);
    }

    @Override
    public void onCreate() {
        cursor = context.getContentResolver().query(
                DatabaseContract.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }
        final long identityToken = Binder.clearCallingIdentity();
        cursor = context.getContentResolver().query(
                DatabaseContract.CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final FavoriteItem moviesItems = getItem(position);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_items);
        Log.d("Widgetku", moviesItems.getTitle());
        Bitmap bmp = null;
        try {
            String urlImages = "http://image.tmdb.org/t/p/original" + moviesItems.getImages();
            bmp = Glide.with(context)
                    .asBitmap()
                    .load(urlImages)
                    .submit()
                    .get();
            rv.setImageViewBitmap(R.id.img_widget_items, bmp);
        } catch (InterruptedException | ExecutionException e) {
            Log.d("Widget Load Error", e.toString());
        }

        Bundle extras = new Bundle();
        extras.putString(MyFvWidget.EXTRA_ITEM, moviesItems.getTitle());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.img_widget_items, fillInIntent);
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return cursor.moveToPosition(position) ? cursor.getLong(0) : position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

