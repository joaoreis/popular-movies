package br.com.joaoreis.popularmovies.home.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Singleton;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.services.MoviesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MovieRepository {

    private static final String TAG = MovieRepository.class.getName();
    private MoviesService moviesService;
    private MutableLiveData<MovieApiResponse> movies;

    public MovieRepository() {
        moviesService = new MoviesService();
        movies = new MutableLiveData<>();
    }

    public LiveData<MovieApiResponse> getMovies(String sortBy) {
        Call<MovieApiResponse> call = moviesService.getMovies(sortBy, MoviesService.API_KEY);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body());
                } else {
                    Log.e(TAG, "onResponse: failed or body is null: \n" + response.isSuccessful() + "\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return movies;
    }
}
