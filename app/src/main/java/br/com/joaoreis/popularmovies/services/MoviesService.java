package br.com.joaoreis.popularmovies.services;

import br.com.joaoreis.popularmovies.BuildConfig;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesService implements TheMovieDBApi {


    public static final String API_KEY = BuildConfig.TmdbApiKey;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final Retrofit retrofit;
    private TheMovieDBApi movieApi;


    public MoviesService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = retrofit.create(TheMovieDBApi.class);
    }

    @Override
    public Call<MovieApiResponse> getMovies(String sortBy, String key) {
        return movieApi.getMovies(sortBy, key);
    }

    @Override
    public Call<TrailerApiResponse> getTrailers(Long movieId, String key) {
        return movieApi.getTrailers(movieId, key);
    }

    @Override
    public Call<ReviewApiResponse> getReviews(Long movieId, String key) {
        return movieApi.getReviews(movieId,key);
    }
}
