package com.example.catalogmoviessub2.TabLayoutFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.catalogmoviessub2.AsyncTask.MyAsyncTask;
import com.example.catalogmoviessub2.BuildConfig;
import com.example.catalogmoviessub2.MoviesItem;
import com.example.catalogmoviessub2.R;
import com.example.catalogmoviessub2.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MoviesItem>> {
    View view;
    ArrayList<MoviesItem> moviesItems;
    MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;


    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        progressBar = view.findViewById(R.id.Progress_Bar);
        recyclerView = view.findViewById(R.id.RcNow_Playing);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        String endpoint = BuildConfig.Base_Url + "movie/now_playing?api_key=" +
                BuildConfig.Api_Key + "&language=" + Locale.getDefault().toString()
                .replaceAll("_", "_");
        //new MyAsyncTask(view.getContext(), recyclerView, endpoint).execute();
        Bundle bundle = new Bundle();
        bundle.putString("endpoint", endpoint);
        getLoaderManager().initLoader(0, bundle, this);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //outState.putParcelableArrayList("dataNowplay", new ArrayList<>(moviesAdapter.getListMovies()));
        Log.d("dataNowplay", String.valueOf(moviesAdapter.getListMovies()));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Loader<ArrayList<MoviesItem>> onCreateLoader(int i, @Nullable Bundle bundle) {
        String endpoint = bundle.getString("endpoint");
        return new MyAsyncTask(getContext(), endpoint);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MoviesItem>> loader, ArrayList<MoviesItem> moviesItems) {
        progressBar.setVisibility(View.GONE);
        Log.d("dataMovies", String.valueOf(moviesItems));
        moviesAdapter = new MoviesAdapter(getContext(), moviesItems);
        //moviesAdapter.setListMovies(moviesItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MoviesItem>> loader) {
    }
}
