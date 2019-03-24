package br.com.joaoreis.popularmovies.services;

import org.junit.Before;
import org.junit.Test;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import retrofit2.Call;

import static org.junit.Assert.assertNotNull;

public class MoviesServiceTest {
    private MoviesService moviesService;

    @Before
    public void setUp() {
        moviesService = new MoviesService();
    }

    @Test
    public void getPopularMovies() {
        Call<MovieApiResponse> call = moviesService.getPopularMovies(MoviesService.API_KEY);
        assertNotNull(call);
    }

    @Test
    public void getTopRatedMovies() {
        Call<MovieApiResponse> call = moviesService.getTopRatedMovies(MoviesService.API_KEY);
        assertNotNull(call);
    }
}