package br.com.joaoreis.popularmovies.moviedetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.view.ui.HomeActivity;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel viewModel;

    private ImageView ivMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieReleaseDate;
    private TextView tvVoteAvg;
    private TextView tvSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setupViews();
        setupViewModel(savedInstanceState);

    }


    private void setupViews() {
        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        tvVoteAvg = findViewById(R.id.tv_vote_avg);
        tvSynopsis = findViewById(R.id.tv_synopsis);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                viewModel.setMovie(movie);
                Toast.makeText(MovieDetailActivity.this, "MovieDetailActivity onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
