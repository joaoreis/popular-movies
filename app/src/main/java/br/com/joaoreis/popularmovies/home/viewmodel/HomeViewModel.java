package br.com.joaoreis.popularmovies.home.viewmodel;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import br.com.joaoreis.popularmovies.database.AppDatabase;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.model.MovieApiResponse;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;

public class HomeViewModel extends AndroidViewModel {

    private final MovieRepository movieRepo;
    private LiveData<MovieApiResponse> movieList;
    private final AppDatabase database;
    private final MutableLiveData<String> sortBy;
    private final LiveData<List<Movie>> favorites;

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private int selectedMenu;

    //TODO: no dependecy injection, refactor
    public HomeViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        movieRepo = new MovieRepository(database);
        movieList = new MutableLiveData<>();
        sortBy = new MutableLiveData<>();
        favorites = Transformations.switchMap(sortBy, new Function<String, LiveData<List<Movie>>>() {
            @Override
            public LiveData<List<Movie>> apply(String input) {
                return getAllFavorites();
            }
        });
    }

    @Inject
    public HomeViewModel(Application application, MovieRepository movieRepository) {
        super(application);
        movieRepo = movieRepository;
        movieList = new MutableLiveData<>();
        database = AppDatabase.getInstance(application);
        sortBy = new MutableLiveData<>();
        favorites = Transformations.switchMap(sortBy, new Function<String, LiveData<List<Movie>>>() {
            @Override
            public LiveData<List<Movie>> apply(String input) {
                return getAllFavorites();
            }
        });
    }

    public int getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(int selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
    
    public LiveData<MovieApiResponse> getPopularMovies() {
        movieList = movieRepo.getMovies(POPULAR);
        return this.movieList;
    }

    public LiveData<MovieApiResponse> getTopRatedMovies() {
        movieList = movieRepo.getMovies(TOP_RATED);
        return this.movieList;
    }

    public LiveData<List<Movie>> getAllFavorites() {
        return movieRepo.getAllFavorites();
    }

    public LiveData<List<Movie>> getFavorites() {
        return favorites;
    }

    public void changeSort(String sort){
        sortBy.postValue(sort);
    }
}