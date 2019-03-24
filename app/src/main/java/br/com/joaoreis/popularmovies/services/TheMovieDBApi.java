package br.com.joaoreis.popularmovies.services;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBApi {

    @GET("popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String key);

    @GET("top_rated")
    Call<MovieApiResponse> getTopRatedMovies(@Query("api_key") String key);
}
