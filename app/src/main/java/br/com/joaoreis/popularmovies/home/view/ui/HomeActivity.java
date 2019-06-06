package br.com.joaoreis.popularmovies.home.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.view.adapter.MovieAdapter;
import br.com.joaoreis.popularmovies.home.view.adapter.OnMovieItemClickListener;
import br.com.joaoreis.popularmovies.home.viewmodel.HomeViewModel;
import br.com.joaoreis.popularmovies.moviedetail.view.ui.MovieDetailActivity;

public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";
    private static final String EXTRA_SCROLL_STATE = "SCROLL_STATE";
    private static int MINIMUM_COLUMNS = 2;
    private static int SCALING_FACTOR = 200;
    private static int N_COLUMNS;
    private static final String TAG = HomeActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MovieAdapter moviePosterAdapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        N_COLUMNS = calculateNoOfColumns();
        setupViews();
        setupViewModel();

    }

    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / SCALING_FACTOR);

        return noOfColumns < MINIMUM_COLUMNS ? MINIMUM_COLUMNS : noOfColumns;
    }

    private void setupViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, N_COLUMNS);
        moviePosterAdapter = new MovieAdapter();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviePosterAdapter);

        moviePosterAdapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_MOVIE, movie);
                intent.putExtra(EXTRA_MOVIE, bundle);

                startActivity(intent);
            }
        });
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getPopularMovies().observe(this, new Observer<MovieApiResponse>() {
            @Override
            public void onChanged(MovieApiResponse movieApiResponse) {
                moviePosterAdapter.setMovies(movieApiResponse.getMovies());
            }
        });

        viewModel.getFavorites().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviePosterAdapter.setMovies(movies);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int optionId = item.getItemId();

        switch (optionId) {
            case R.id.action_refresh:
                return true;

            case R.id.action_popular:
                viewModel.getPopularMovies();
                return true;

            case R.id.action_topRated:
                viewModel.getTopRatedMovies();
                return true;

            case R.id.action_favorites:
                viewModel.changeSort("Favorites");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Parcelable scrollState = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(EXTRA_SCROLL_STATE, scrollState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Parcelable scrollState = savedInstanceState.getParcelable(EXTRA_SCROLL_STATE);
        recyclerView.getLayoutManager().onRestoreInstanceState(scrollState);
    }
}
