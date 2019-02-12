package com.example.catalogmoviessub2.AsyncTask;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.catalogmoviessub2.MoviesItem;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTask extends android.support.v4.content.AsyncTaskLoader<ArrayList<MoviesItem>> {
    private ArrayList<MoviesItem> moviesItems;
    private Context context;
    private String mDaftarMovies;
    private boolean hasResult = false;

    public MyAsyncTask(Context context, String endpoint) {
        super(context);
        onContentChanged();
        Log.d("Url-Endpoint", endpoint);
        this.context = context;
        this.mDaftarMovies = endpoint;
    }

    @Nullable
    @Override
    public ArrayList<MoviesItem> loadInBackground() {
        final ArrayList<MoviesItem> list = new ArrayList<>();

        SyncHttpClient client = new SyncHttpClient();
        client.get(mDaftarMovies, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject weather = jsonArray.getJSONObject(i);
                        MoviesItem data = new MoviesItem(weather);
                        list.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return list;
    }

    @Override
    public void deliverResult(@Nullable ArrayList<MoviesItem> data) {
        moviesItems = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(moviesItems);
            moviesItems = null;
            hasResult = false;
        }
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(moviesItems);
    }

    private void onReleaseResources(ArrayList<MoviesItem> moviesItems) {

    }

}
