package br.com.joaoreis.popularmovies.moviedetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.view.ui.HomeActivity;

import static br.com.joaoreis.popularmovies.home.view.adapter.MovieAdapter.BASE_URL;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel viewModel;

    private ImageView ivMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieReleaseDate;
    private TextView tvVoteAvg;
    private TextView tvOverview;

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

        loadMovieData(movie);
    }


    private void setupViews() {
        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        tvVoteAvg = findViewById(R.id.tv_vote_avg);
        tvOverview = findViewById(R.id.tv_overview);
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
