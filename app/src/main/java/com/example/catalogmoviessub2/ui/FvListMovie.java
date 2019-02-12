package com.example.catalogmoviessub2.ui;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.catalogmoviessub2.Helper.FvHelper;
import com.example.catalogmoviessub2.R;
import com.example.catalogmoviessub2.adapter.RecycleAdapter;

import static com.example.catalogmoviessub2.Helper.DatabaseContract.CONTENT_URI;

public class FvListMovie extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FvHelper favHelper;
    private RecycleAdapter favAdapter;
    private Cursor list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fv_list_movie);

        progressBar = findViewById(R.id.FvProgressbar);
        favHelper = new FvHelper(this);

        progressBar.setVisibility(View.VISIBLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Favorit List");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.RVFvListMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        favHelper.Open();
        favAdapter = new RecycleAdapter(this);
        favAdapter.setListMovies(list);
        recyclerView.setAdapter(favAdapter);
        new LoadFavAsync().execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (favHelper != null) {
            favHelper.close();
        }
    }

    private class LoadFavAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(CONTENT_URI, null, null
                    , null, null);

        }

        @Override
        protected void onPostExecute(Cursor moviesItems) {
            super.onPostExecute(moviesItems);
            progressBar.setVisibility(View.GONE);

            list = moviesItems;
            favAdapter.setListMovies(list);
            favAdapter.notifyDataSetChanged();
        }
    }
}
