package com.example.catalogmoviessub2.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class DetailsMovies extends AppCompatActivity {
    private MoviesItem items;
    private FvHelper fvHelper;
    private ImageView imageView, imPoster;
    private TextView txtName, txtDesc, txtRealese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);
        items = getIntent().getParcelableExtra("senddata2");
        Log.d("senddata2", items + "");

        getSupportActionBar().setTitle(items.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fvHelper = new FvHelper(this);
        imageView = findViewById(R.id.img_backdrop);
        imPoster = findViewById(R.id.imgPoster);
        txtName = findViewById(R.id.txt_name_detail);
        txtName.setText(items.getTitle());
        txtRealese = findViewById(R.id.txt_realeas);
        txtRealese.setText(items.getRealease());
        txtDesc = findViewById(R.id.txt_overview_detail);
        txtDesc.setText(items.getOverview());
        Uri url_image = Uri.parse("https://image.tmdb.org/t/p/w185" + items.getImages());
        Uri url_image2 = Uri.parse("https://image.tmdb.org/t/p/w185" + items.getPoster());
        Glide.with(this)
                .load(url_image)
                .into(imageView);
        Glide.with(this)
                .load(url_image2)
                .into(imPoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);
        fvHelper.Open();
        if (!fvHelper.isFavorit(items.getTitle())) {
            menu.findItem(R.id.details_menu).setIcon(R.drawable.ic_favorite_red_24dp);
        } else {
            menu.findItem(R.id.details_menu).setIcon(R.drawable.ic_favorite_black_24dp);
        }
        fvHelper.close();
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
        fvHelper.Open();
        if (fvHelper.isFavorit(items.getTitle())) {
            MoviesItem newNote = new MoviesItem();
            newNote.setId(items.getId());
            newNote.setTitle(items.getTitle());
            newNote.setOverview(items.getOverview());
            newNote.setRealease(items.getRealease());
            newNote.setImages(items.getImages());
            newNote.setPoster(items.getPoster());
            fvHelper.insert(newNote);
            Log.d("Favadd", items.getTitle() + "");

            Toast.makeText(this, "Favorit Ditambahkan ", Toast.LENGTH_LONG).show();
            menuItem.setIcon(ContextCompat.getDrawable(this,
                    R.drawable.ic_favorite_red_24dp));
        } else {
            Log.d("FavDel", items.getTitle() + "");

            fvHelper.delete(items.getTitle());
            Toast.makeText(this, "Favorit DIhapus ", Toast.LENGTH_LONG).show();
            menuItem.setIcon(ContextCompat.getDrawable(this,
                    R.drawable.ic_favorite_black_24dp));
        }
        fvHelper.close();
    }
}
