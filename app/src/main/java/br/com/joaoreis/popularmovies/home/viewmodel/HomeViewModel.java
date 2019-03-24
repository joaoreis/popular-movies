package br.com.joaoreis.popularmovies.home.viewmodel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepo;
    private LiveData<MovieApiResponse> movieList;

    //TODO: no dependecy injection, refactor
    public HomeViewModel() {
        movieRepo = new MovieRepository();
        movieList = new MutableLiveData<>();
    }

    @Inject
    public HomeViewModel(MovieRepository movieRepository) {
        movieRepo = movieRepository;
    }

    public LiveData<MovieApiResponse> getPopularMovies() {
        movieList = movieRepo.getPopularMovies();
        return this.movieList;
    }

    public LiveData<MovieApiResponse> getTopRatedMovies() {
        movieList = movieRepo.getTopRatedMovies();
        return this.movieList;
    }
}
