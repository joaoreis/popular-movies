package br.com.joaoreis.popularmovies.home.repository;

import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;

import static org.junit.Assert.assertNotNull;

public class MovieRepositoryTest {
    private MovieRepository repository;

    @Before
    public void setUp() {
        repository = new MovieRepository();
    }

    @Test
    public void getPopularMovies() {
        LiveData<MovieApiResponse> movies = repository.getPopularMovies();
        assertNotNull(movies);
        System.out.println("getPopularMovies: " + movies.toString() );
    }
}