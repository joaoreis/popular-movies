package br.com.joaoreis.popularmovies.moviedetail.view.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import br.com.joaoreis.popularmovies.database.Favorite;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.view.ui.HomeActivity;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.Trailer;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.view.adapter.OnTrailerItemClickListener;
import br.com.joaoreis.popularmovies.moviedetail.view.adapter.ReviewAdapter;
import br.com.joaoreis.popularmovies.moviedetail.view.adapter.TrailerAdapter;
import br.com.joaoreis.popularmovies.moviedetail.viewmodel.MovieDetailViewModel;

import static br.com.joaoreis.popularmovies.home.view.adapter.MovieAdapter.BASE_URL;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String YOUTUBE_URL = "www.youtube.com";
    private MovieDetailViewModel viewModel;

    private ImageView ivMoviePoster;
    private ImageView ivStar;
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
        findViews();
        setupRecyclerViews();
        setupClickListeners();
    }

    private void findViews() {
        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        tvVoteAvg = findViewById(R.id.tv_vote_avg);
        tvOverview = findViewById(R.id.tv_overview);
        ivStar = findViewById(R.id.iv_star);
        reviewsRecyclerView = findViewById(R.id.reviews_recyclerview);
        trailersRecyclerView = findViewById(R.id.trailers_recyclerview);
    }

    private void setupRecyclerViews() {
        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(this);
        reviewAdapter = new ReviewAdapter();
        trailerAdapter = new TrailerAdapter();

        reviewsRecyclerView.setLayoutManager(reviewLayoutManager);
        reviewsRecyclerView.setAdapter(reviewAdapter);
        reviewsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        trailersRecyclerView.setLayoutManager(trailerLayoutManager);
        trailersRecyclerView.setAdapter(trailerAdapter);
        trailersRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setupClickListeners() {
        trailerAdapter.setOnItemClickListener(new OnTrailerItemClickListener() {
            @Override
            public void onItemClick(Trailer trailer) {

                Uri trailerUri = new Uri.Builder()
                        .scheme("http")
                        .appendPath(YOUTUBE_URL)
                        .appendPath("watch")
                        .appendQueryParameter("v", trailer.key)
                        .build();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(trailerUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieDetailActivity.this, "Added to Favorite!", Toast.LENGTH_SHORT).show();
                ((ImageView) v).setImageResource(R.drawable.ic_star_yellow_48dp);

                Movie movie = viewModel.getMovie().getValue();
                Favorite favorite = new Favorite(movie.getId(), movie.getTitle());

            }
        });
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
