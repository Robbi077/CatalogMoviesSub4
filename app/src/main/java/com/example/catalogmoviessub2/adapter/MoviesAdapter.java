package com.example.catalogmoviessub2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.catalogmoviessub2.MoviesItem;
import com.example.catalogmoviessub2.R;
import com.example.catalogmoviessub2.ui.DetailsMovies;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.myViewholder> {
    static final String Extras_Movies = "Extras_Movies";
    private Context context;
    private ArrayList<MoviesItem> listMovies;


    public MoviesAdapter(Context context, ArrayList<MoviesItem> moviesItems) {
        this.context = context;
        this.listMovies = moviesItems;
    }

    public MoviesAdapter(Activity activity) {
        this.context = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardview, viewGroup, false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder myViewholder, final int i) {
        myViewholder.txtTitle.setText(listMovies.get(i).getTitle());
        if (TextUtils.isEmpty(listMovies.get(i).getOverview())) {
            String kelompok_Desc = ".....";
            myViewholder.txtDetail.setText(kelompok_Desc);
        } else {
            String potongan = listMovies.get(i).getOverview().substring(0, 50);
            String kelompok_Desc = potongan + ".....";
            myViewholder.txtDetail.setText(kelompok_Desc);
        }
        myViewholder.txtRealease.setText(listMovies.get(i).getRealease());
        Uri url_image = Uri.parse("https://image.tmdb.org/t/p/w185" + listMovies.get(i).getPoster());
        Glide.with(context)
                .load(url_image)
                .apply(new RequestOptions().override(350, 550))
                .into(myViewholder.imageView);

        myViewholder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", listMovies.get(i).getTitle().toString());
                Toast.makeText(context, "Share " + listMovies.get(i).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        myViewholder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsMovies.class);
                MoviesItem m = listMovies.get(i);
                intent.putExtra("senddata2", m);
                v.getContext().startActivity(intent);
            }
        });
        myViewholder.relativeLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsMovies.class);
                MoviesItem m = listMovies.get(i);
                intent.putExtra("senddata2", m);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public ArrayList<MoviesItem> getListMovies() {
        Log.d("listMovies", String.valueOf(listMovies));
        return listMovies;
    }

    public void setListMovies(ArrayList<MoviesItem> moviesItems) {
        this.listMovies = moviesItems;
    }

    class myViewholder extends RecyclerView.ViewHolder {
        Button btnDetail, btnShare;
        TextView txtTitle, txtDetail, txtRealease;
        ImageView imageView;
        RelativeLayout relativeLayoutItem;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_name);
            txtDetail = itemView.findViewById(R.id.txt_overview);
            txtRealease = itemView.findViewById(R.id.txt_realeas);
            imageView = itemView.findViewById(R.id.imgPoster);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnShare = itemView.findViewById(R.id.btn_share);
            relativeLayoutItem = itemView.findViewById(R.id.relativeLayoutItem);
        }
    }


}
