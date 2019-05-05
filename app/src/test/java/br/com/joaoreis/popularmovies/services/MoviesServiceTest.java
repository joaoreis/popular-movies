package br.com.joaoreis.popularmovies.services;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MoviesServiceTest {
    private MoviesService moviesService;

    @Before
    public void setUp() {
        moviesService = new MoviesService();
    }

    @Test
    public void getPopularMovies() throws IOException {
        Call<MovieApiResponse> call = moviesService.getPopularMovies(MoviesService.API_KEY);
        assertNotNull(call);
        Response<MovieApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());

    }

    @Test
    public void getTopRatedMovies() throws IOException {
        Call<MovieApiResponse> call = moviesService.getTopRatedMovies(MoviesService.API_KEY);
        assertNotNull(call);
        Response<MovieApiResponse> response = call.execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }
}