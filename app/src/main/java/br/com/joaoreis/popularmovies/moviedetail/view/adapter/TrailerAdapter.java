package br.com.joaoreis.popularmovies.moviedetail.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.moviedetail.model.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerAdapterViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private OnTrailerItemClickListener listener;


    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.rv_trailer_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(layoutId, parent, shouldAttach);
        return new TrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapterViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.trailerName.setText(trailer.name);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailerAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView trailerName;
        private ImageView playButton;

        public TrailerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerName = itemView.findViewById(R.id.tv_trailer_name);
            playButton = itemView.findViewById(R.id.iv_play_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(trailers.get(position));
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnTrailerItemClickListener listener) {
        this.listener = listener;
    }
}
