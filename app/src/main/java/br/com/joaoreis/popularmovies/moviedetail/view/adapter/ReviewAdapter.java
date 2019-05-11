package br.com.joaoreis.popularmovies.moviedetail.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.moviedetail.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.rv_review_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(layoutId, parent, shouldAttach);
        return new ReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapterViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.author.setText(review.author);
        holder.review.setText(review.content);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView review;

        public ReviewAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_author);
            review = itemView.findViewById(R.id.tv_review);
        }
    }
}
