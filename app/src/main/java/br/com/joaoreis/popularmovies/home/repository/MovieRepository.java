package br.com.joaoreis.popularmovies.home.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Singleton;

import br.com.joaoreis.popularmovies.database.AppDatabase;
import br.com.joaoreis.popularmovies.database.AppExecutors;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
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
    private final MutableLiveData<ReviewApiResponse> reviews;
    private MutableLiveData<MovieApiResponse> movies;
    private final MutableLiveData<TrailerApiResponse> trailers;
    private final AppDatabase database;

    public MovieRepository(AppDatabase database) {
        moviesService = new MoviesService();
        movies = new MutableLiveData<>();
        trailers = new MutableLiveData<>();
        reviews = new MutableLiveData<>();
        this.database = database;
    }

    public LiveData<MovieApiResponse> getMovies(String sortBy) {
        Call<MovieApiResponse> call = moviesService.getMovies(sortBy, apiKey);

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movies.postValue(response.body());
                } else {
                    Log.e(TAG, "onResponse: getMovies failed or body is null: \n" + response.isSuccessful() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return movies;
    }

    public LiveData<TrailerApiResponse> getTrailers(Long movieId) {
        Call<TrailerApiResponse> call = moviesService.getTrailers(movieId, apiKey);
        call.enqueue(new Callback<TrailerApiResponse>() {
            @Override
            public void onResponse(Call<TrailerApiResponse> call, Response<TrailerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    trailers.postValue(response.body());
                } else {
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

    public LiveData<ReviewApiResponse> getReviews(Long movieId) {
        Call<ReviewApiResponse> call = moviesService.getReviews(movieId, apiKey);
        call.enqueue(new Callback<ReviewApiResponse>() {
            @Override
            public void onResponse(Call<ReviewApiResponse> call, Response<ReviewApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reviews.postValue(response.body());
                } else {
                    Log.e(TAG, "onResponse: getReviewList failed or body is null: \n" + response.isSuccessful() + "\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ReviewApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return reviews;
    }

    public LiveData<Movie> getFavoriteById(final long movieId) {

        final MediatorLiveData<Movie> favorite = new MediatorLiveData<>();
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                favorite.addSource(database.favoriteDao().getFavoriteById(movieId), new Observer<Movie>() {
                    @Override
                    public void onChanged(Movie movie) {
                        favorite.postValue(movie);
                    }
                });

            }
        });

        return favorite;
    }
}
