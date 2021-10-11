package br.com.joaoreis.popularmovies.services;

import org.junit.*;

import java.io.IOException;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore("Api key messing up")
public class MoviesServiceTest {
    private MoviesService moviesService;
    private final String apiKey = MoviesService.API_KEY;

    @Before
    public void setUp() {
        moviesService = new MoviesService();
    }

    @Test
    public void getPopularMovies() throws IOException {
        Call<MovieApiResponse> call = moviesService.getMovies("popular", apiKey);
        assertNotNull(call);
        Response<MovieApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());

    }

    @Test
    public void getTopRatedMovies() throws IOException {
        Call<MovieApiResponse> call = moviesService.getMovies("top_rated", apiKey);
        assertNotNull(call);
        Response<MovieApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }

    @Test
    public void gettrailers() throws IOException {
        Long movieId = 550L;
        Call<TrailerApiResponse> call = moviesService.getTrailers(movieId, apiKey);
        assertNotNull(call);
        Response<TrailerApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }

    @Test
    public void getReviews() throws IOException {
        Long movieId = 550L;
        Call<ReviewApiResponse> call = moviesService.getReviews(movieId, apiKey);
        Response<ReviewApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }

}
