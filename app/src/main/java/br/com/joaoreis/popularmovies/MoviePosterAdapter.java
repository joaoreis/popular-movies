package br.com.joaoreis.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterAdapterViewHolder> {

    @NonNull
    @Override
    public MoviePosterAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.rv_movie_poster;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(layoutId, viewGroup, shouldAttach);
        return new MoviePosterAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterAdapterViewHolder holder, int position) {

        Picasso.get()
                .load(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.poster)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class MoviePosterAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        public MoviePosterAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.iv_movie_poster);
        }
    }
}

/*
Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.sad_sandwhich)
                .into(ingredientsIv);
* */

/*
* class PlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView mPlace;

    public PlaceViewHolder(View itemView) {
        super(itemView);

        mPlace = itemView.findViewById(R.id.ivPlace);
    }
}
* */