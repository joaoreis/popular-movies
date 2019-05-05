package br.com.joaoreis.popularmovies.home.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Singleton;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewsApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import br.com.joaoreis.popularmovies.services.MoviesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MovieRepository {

    private static final String TAG = MovieRepository.class.getName();
    private final String apiKey = MoviesService.API_KEY;
    private MoviesService moviesService;
    private final MutableLiveData<ReviewsApiResponse> reviews;
    private MutableLiveData<MovieApiResponse> movies;
    private final MutableLiveData<TrailerApiResponse> trailers;

    public MovieRepository() {
        moviesService = new MoviesService();
        movies = new MutableLiveData<>();
        trailers = new MutableLiveData<>();
        reviews = new MutableLiveData<>();
    }

    public LiveData<MovieApiResponse> getMovies(String sortBy) {
        Call<MovieApiResponse> call = moviesService.getMovies(sortBy, apiKey);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body());
                } else {
                    Log.e(TAG, "onResponse: getMovies failed or body is null: \n" + response.isSuccessful() + "\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return movies;
    }

    public LiveData<TrailerApiResponse> getTrailers(int movieId) {
        Call<TrailerApiResponse> call = moviesService.getTrailers(movieId, apiKey);
        call.enqueue(new Callback<TrailerApiResponse>() {
            @Override
            public void onResponse(Call<TrailerApiResponse> call, Response<TrailerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    trailers.postValue(response.body());
                }
                else {
                    Log.e(TAG, "onResponse: getTrailers failed or body is null: \n" + response.isSuccessful() + "\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<TrailerApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return trailers;
    }

    public LiveData<ReviewsApiResponse> getReviews(int movieId) {
        Call<ReviewsApiResponse> call = moviesService.getReviews(movieId, apiKey);
        call.enqueue(new Callback<ReviewsApiResponse>() {
            @Override
            public void onResponse(Call<ReviewsApiResponse> call, Response<ReviewsApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reviews.postValue(response.body());
                }
                else {
                    Log.e(TAG, "onResponse: getReviews failed or body is null: \n" + response.isSuccessful() + "\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ReviewsApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return reviews;
    }
}
