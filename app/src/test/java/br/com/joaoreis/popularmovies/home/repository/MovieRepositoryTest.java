package br.com.joaoreis.popularmovies.home.repository;

import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.joaoreis.popularmovies.database.AppDatabase;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;
import br.com.joaoreis.popularmovies.services.MoviesService;

import static org.junit.Assert.assertNotNull;

public class MovieRepositoryTest {
    private MovieRepository repository;

    @Mock
    private AppDatabase database;

    @Before
    public void setUp() {
        repository = new MovieRepository(database);
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
        Long movieId = 550L;
        LiveData<TrailerApiResponse> trailer = repository.getTrailers(movieId);
        assertNotNull(trailer);
        System.out.println("getTrailers: " + trailer.toString() );
    }

}
