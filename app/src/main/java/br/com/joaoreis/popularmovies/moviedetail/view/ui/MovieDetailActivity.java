package br.com.joaoreis.popularmovies.moviedetail.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.view.ui.HomeActivity;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.view.adapter.ReviewAdapter;
import br.com.joaoreis.popularmovies.moviedetail.view.adapter.TrailerAdapter;
import br.com.joaoreis.popularmovies.moviedetail.viewmodel.MovieDetailViewModel;

import static br.com.joaoreis.popularmovies.home.view.adapter.MovieAdapter.BASE_URL;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel viewModel;

    private ImageView ivMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieReleaseDate;
    private TextView tvVoteAvg;
    private TextView tvOverview;

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;

    private RecyclerView trailersRecyclerView;
    private TrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        setupViews();
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        Intent callingIntent = getIntent();
        Bundle extras = callingIntent.getBundleExtra(HomeActivity.EXTRA_MOVIE);
        Movie movie = extras.getParcelable(HomeActivity.EXTRA_MOVIE);
        viewModel.setMovie(movie);
        setupViewModel();

        loadMovieData(movie);
    }

    private void setupViews() {
        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(this);
        reviewAdapter = new ReviewAdapter();
        trailerAdapter = new TrailerAdapter();

        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        tvVoteAvg = findViewById(R.id.tv_vote_avg);
        tvOverview = findViewById(R.id.tv_overview);

        reviewsRecyclerView = findViewById(R.id.reviews_recyclerview);
        reviewsRecyclerView.setLayoutManager(reviewLayoutManager);
        reviewsRecyclerView.setAdapter(reviewAdapter);
        reviewsRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        reviewsRecyclerView = findViewById(R.id.trailers_recyclerview);
        reviewsRecyclerView.setLayoutManager(trailerLayoutManager);
        reviewsRecyclerView.setAdapter(trailerAdapter);
        reviewsRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void setupViewModel() {
        viewModel.getReviewList().observe(this, new Observer<ReviewApiResponse>() {
            @Override
            public void onChanged(ReviewApiResponse reviewApiResponse) {
                reviewAdapter.setReviews(reviewApiResponse.getReviews());
            }
        });

        viewModel.getTrailerList().observe(this, new Observer<TrailerApiResponse>() {
            @Override
            public void onChanged(TrailerApiResponse trailerApiResponse) {
                trailerAdapter.setTrailers(trailerApiResponse.getTrailers());
            }
        });
    }

    private void loadMovieData(Movie movie) {
        Picasso.get()
                .load(BASE_URL + movie.getPosterPath())
                .placeholder(R.color.black)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivMoviePoster);

        Date releaseDate = movie.getReleaseDate();

        tvMovieTitle.setText(movie.getTitle());
        tvMovieReleaseDate.setText(new SimpleDateFormat("yyyy", Locale.getDefault()).format(releaseDate));
        tvVoteAvg.setText(String.valueOf(movie.getVoteAverage()));
        tvOverview.setText(movie.getOverview());
    }
}
