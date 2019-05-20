package br.com.joaoreis.popularmovies.services;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDBApi {

    @GET("{sort_by}")
    Call<MovieApiResponse> getMovies(@Path("sort_by") String sortBy, @Query("api_key") String key);

    @GET("{movie_id}/videos")
    Call<TrailerApiResponse> getTrailers(@Path("movie_id") Long movieId, @Query("api_key") String key);

    @GET("{movie_id}/reviews")
    Call<ReviewApiResponse> getReviews(@Path("movie_id") Long movieId, @Query("api_key") String key);


}
