package br.com.joaoreis.popularmovies;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.joaoreis.popularmovies.model.MovieApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int N_COLUMNS = 2;
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    MoviePosterAdapter moviePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        callMovieAPi();

    }

    private void callMovieAPi() {
        Call<MovieApiResponse> call = new RetrofitApi().getMovieService().getPopularMovies(MoviesService.API_KEY);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String test = response.body().toString();
                    Log.d(TAG, "onResponse: " + test);
                }
                else {
                    Log.d(TAG, "onResponse: " + "ELSE");
                }

            }


            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ERROR");
            }
        });
    }

    private void setupViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, N_COLUMNS);
        moviePosterAdapter = new MoviePosterAdapter();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviePosterAdapter);

    }
}
