package com.example.catalogmoviessub2.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.catalogmoviessub2.Helper.FvHelper;
import com.example.catalogmoviessub2.MoviesItem;
import com.example.catalogmoviessub2.R;

public class FvDetailMovie extends AppCompatActivity {
    public static final String EXTRAS_TITLE = "EXTRAS_TITLE";
    public static final String EXTRAS_DETAIL = "EXTRAS_DETAIL";
    public static final String EXTRAS_RELEASE = "EXTRAS_RELEASE";
    public static final String EXTRAS_IMAGEsS = "EXTRAS_IMAGES";
    private ImageView imageView;
    private TextView tv_Title, tv_OverView, tv_Release;
    private FvHelper mfavHelper;
    private String title, details, release, images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra(EXTRAS_TITLE);
        details = getIntent().getStringExtra(EXTRAS_DETAIL);
        release = getIntent().getStringExtra(EXTRAS_RELEASE);
        images = getIntent().getStringExtra(EXTRAS_IMAGEsS);

        mfavHelper = new FvHelper(this);
        mfavHelper.Open();
        setContentView(R.layout.activity_fv_detail_movie);
        imageView = findViewById(R.id.fv_detail_img);
        tv_Title = findViewById(R.id.fv_detail_title);
        tv_OverView = findViewById(R.id.fv_detail_desc);
        tv_Release = findViewById(R.id.fv_detail_release);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_Title.setText(title);
        tv_OverView.setText(details);
        tv_Release.setText(release);
        getSupportActionBar().setTitle(title);
        String urlImages = "http://image.tmdb.org/t/p/w185" + images;

        Glide.with(this)
                .load(Uri.parse(urlImages))
                .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        if (!mfavHelper.isFavorit(title)) {
            menu.findItem(R.id.details_menu).setIcon(R.drawable.ic_favorite_black_24dp);
        } else {
            menu.findItem(R.id.details_menu).setIcon(R.drawable.ic_favorite_black_24dp);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.details_menu:
                FavFunction(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void FavFunction(MenuItem menuItem) {
        if (mfavHelper.isFavorit(title)) {
            MoviesItem newNote = new MoviesItem();
            newNote.setTitle(title);
            newNote.setOverview(details);
            newNote.setRealease(release);
            newNote.setImages(images);
            mfavHelper.insert(newNote);
            Toast.makeText(this, "Favorit Ditambahkan ", Toast.LENGTH_LONG).show();
            menuItem.setIcon(ContextCompat.getDrawable(this,
                    R.drawable.ic_favorite_red_24dp));
        } else {
            mfavHelper.delete(title);
            Toast.makeText(this, "Favorit Dihapus ", Toast.LENGTH_LONG).show();
            menuItem.setIcon(ContextCompat.getDrawable(this,
                    R.drawable.ic_favorite_black_24dp));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mfavHelper != null) {
            mfavHelper.close();
        }
    }
}
