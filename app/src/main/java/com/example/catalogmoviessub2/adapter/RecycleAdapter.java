package com.example.catalogmoviessub2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.catalogmoviessub2.FavoriteItem;
import com.example.catalogmoviessub2.R;
import com.example.catalogmoviessub2.ui.FvDetailMovie;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    public static final String EXTRAS_TITLE = "EXTRAS_TITLE";
    public static final String EXTRAS_DETAIL = "EXTRAS_DETAIL";
    public static final String EXTRAS_RELEASE = "EXTRAS_RELEASE";
    public static final String EXTRAS_IMAGEsS = "EXTRAS_IMAGES";
    static final String Extras_Movies = "Extras_Movies";
    private Activity activity;
    private Cursor listMovies;

    public RecycleAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final FavoriteItem listMovies = getItem(i);
        Log.d("ContentSQL", listMovies.getTitle() + "");
        myViewHolder.txtTitle.setText(listMovies.getTitle());
        if (TextUtils.isEmpty(listMovies.getDetail())) {
            String kelompok_Desc = ".....";
            myViewHolder.txtDetail.setText(kelompok_Desc);
        } else {
            String potongan = listMovies.getDetail().substring(0, 50);
            String kelompok_Desc = potongan + ".....";
            myViewHolder.txtDetail.setText(kelompok_Desc);
        }
        myViewHolder.txtRealease.setText(listMovies.getRealeaseDate());
        myViewHolder.txtRealease.setText(listMovies.getRealeaseDate());
        Glide.with(activity)
                .load(listMovies.getImages())
                .apply(new RequestOptions().override(350, 550))
                .into(myViewHolder.imageView);
        myViewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", listMovies.getTitle().toString());
                Toast.makeText(activity, "Share " + i, Toast.LENGTH_LONG).show();
            }
        });
        myViewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FvDetailMovie.class);
                intent.putExtra(EXTRAS_TITLE, listMovies.getTitle());
                intent.putExtra(EXTRAS_DETAIL, listMovies.getDetail());
                intent.putExtra(EXTRAS_RELEASE, listMovies.getRealeaseDate());
                intent.putExtra(EXTRAS_IMAGEsS, listMovies.getImages());
                v.getContext().startActivity(intent);
            }
        });
    }


    private FavoriteItem getItem(int position) {
        if (!listMovies.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new FavoriteItem(listMovies);
    }

    @Override
    public int getItemCount() {
        if (listMovies == null) return 0;
        return listMovies.getCount();
    }

    public void setListMovies(Cursor list) {
        this.listMovies = list;
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        Button btnDetail, btnShare;
        TextView txtTitle, txtDetail, txtRealease;
        ImageView imageView;
        RelativeLayout relativeLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_name);
            txtDetail = itemView.findViewById(R.id.txt_overview);
            txtRealease = itemView.findViewById(R.id.txt_realeas);
            imageView = itemView.findViewById(R.id.imgPoster);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnShare = itemView.findViewById(R.id.btn_share);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutItem);
        }
    }
}
