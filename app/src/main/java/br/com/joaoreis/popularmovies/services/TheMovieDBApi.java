package br.com.joaoreis.popularmovies.services;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDBApi {

    @GET("{sort_by}")
    Call<MovieApiResponse> getMovies(@Path("sort_by") String sortBy, @Query("api_key") String key);
}
