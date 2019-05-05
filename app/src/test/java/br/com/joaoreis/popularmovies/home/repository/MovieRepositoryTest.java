package br.com.joaoreis.popularmovies.home.repository;

import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;

import static org.junit.Assert.assertNotNull;

public class MovieRepositoryTest {
    private MovieRepository repository;

    @Before
    public void setUp() {
        repository = new MovieRepository();
    }

    @Test
    public void getPopularMovies() {
        LiveData<MovieApiResponse> movies = repository.getMovies("popular");
        assertNotNull(movies);
        System.out.println("getMovies: " + movies.toString() );
    }

    @Test
    public void getTopratedMovies() {
        LiveData<MovieApiResponse> movies = repository.getMovies("top_rated");
        assertNotNull(movies);
        System.out.println("getMovies: " + movies.toString() );
    }

    @Test
    public void getTrailers() {
        int movieId = 550;
        LiveData<TrailerApiResponse> trailer = repository.getTrailers(movieId);
        assertNotNull(trailer);
        System.out.println("getTrailers: " + trailer.toString() );
    }

}