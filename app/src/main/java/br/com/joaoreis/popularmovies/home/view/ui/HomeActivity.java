package br.com.joaoreis.popularmovies.home.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.joaoreis.popularmovies.R;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.view.adapter.MovieAdapter;
import br.com.joaoreis.popularmovies.home.view.adapter.OnItemClickListener;
import br.com.joaoreis.popularmovies.home.viewmodel.HomeViewModel;
import br.com.joaoreis.popularmovies.moviedetail.MovieDetailActivity;

public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";
    private static final int N_COLUMNS = 2;
    private static final String TAG = HomeActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MovieAdapter moviePosterAdapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupViewModel();

    }

    private void setupViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, N_COLUMNS);
        moviePosterAdapter = new MovieAdapter();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviePosterAdapter);

        moviePosterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_MOVIE, movie);
                intent.putExtra(EXTRA_MOVIE,bundle);

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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
