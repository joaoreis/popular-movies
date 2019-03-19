package br.com.joaoreis.popularmovies;

import br.com.joaoreis.popularmovies.model.MovieApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MoviesService {


    String API_KEY = BuildConfig.TmdbApiKey;
    @GET("popular")
    Call<MovieApiResponse> getPopularMovies(@Query("api_key") String key);

    @GET("top_rated")
    Call<MovieApiResponse> getTopRatedMovies();
}
