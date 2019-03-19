package br.com.joaoreis.popularmovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";//popular?api_key="key"

    private final Retrofit retrofit;


    public RetrofitApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MoviesService getMovieService() {
        return this.retrofit.create(MoviesService.class);
    }

}
