package br.com.joaoreis.popularmovies.home.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.com.joaoreis.popularmovies.database.AppDatabase;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;

public class HomeViewModel extends AndroidViewModel {

    private final MovieRepository movieRepo;
    private LiveData<MovieApiResponse> movieList;
    private final AppDatabase database;

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    //TODO: no dependecy injection, refactor
    public HomeViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        movieRepo = new MovieRepository(database);
        movieList = new MutableLiveData<>();

    }

    @Inject
    public HomeViewModel(Application application, MovieRepository movieRepository) {
        super(application);
        movieRepo = movieRepository;
        movieList = new MutableLiveData<>();
        database = AppDatabase.getInstance(application);
    }

    public LiveData<MovieApiResponse> getPopularMovies() {
        movieList = movieRepo.getMovies(POPULAR);
        return this.movieList;
    }

    public LiveData<MovieApiResponse> getTopRatedMovies() {
        movieList = movieRepo.getMovies(TOP_RATED);
        return this.movieList;
    }

    public LiveData<MovieApiResponse> getAllFavorites() {

        movieList = movieRepo.getAllFavorites();
        return this.movieList;
    }

}
