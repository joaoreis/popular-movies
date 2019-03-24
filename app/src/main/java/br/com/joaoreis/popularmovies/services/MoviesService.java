package br.com.joaoreis.popularmovies.services;

import br.com.joaoreis.popularmovies.BuildConfig;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
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
    public Call<MovieApiResponse> getPopularMovies(String key) {
        return movieApi.getPopularMovies(API_KEY);
    }

    @Override
    public Call<MovieApiResponse> getTopRatedMovies(String key) {
        return movieApi.getTopRatedMovies(API_KEY);
    }
}
