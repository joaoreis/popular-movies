package br.com.joaoreis.popularmovies.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepo;
    private LiveData<MovieApiResponse> movieList;

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    //TODO: no dependecy injection, refactor
    public HomeViewModel() {
        movieRepo = new MovieRepository();
        movieList = new MutableLiveData<>();
    }

    @Inject
    public HomeViewModel(MovieRepository movieRepository) {
        movieRepo = movieRepository;
        movieList = new MutableLiveData<>();
    }

    public LiveData<MovieApiResponse> getPopularMovies() {
        movieList = movieRepo.getMovies(POPULAR);
        return this.movieList;
    }

    public LiveData<MovieApiResponse> getTopRatedMovies() {
        movieList = movieRepo.getMovies(TOP_RATED);
        return this.movieList;
    }
}
