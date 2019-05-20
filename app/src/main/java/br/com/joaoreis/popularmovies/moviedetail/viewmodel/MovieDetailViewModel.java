package br.com.joaoreis.popularmovies.moviedetail.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.com.joaoreis.popularmovies.database.AppDatabase;
import br.com.joaoreis.popularmovies.database.AppExecutors;
import br.com.joaoreis.popularmovies.database.Favorite;
import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MovieRepository movieRepo;
    private final AppDatabase database;

    private MutableLiveData<Movie> movie;
    private LiveData<ReviewApiResponse> reviewList;
    private LiveData<TrailerApiResponse> trailerList;
    private LiveData<Favorite> favorite;
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public MovieDetailViewModel(Application application, Movie movie) {
        super(application);
        database = AppDatabase.getInstance(application);
        movieRepo = new MovieRepository(database);
        this.movie = new MutableLiveData<>();
        this.movie.postValue(movie);
        reviewList = new MutableLiveData<>();
        trailerList = new MutableLiveData<>();

        favorite = new MutableLiveData<>();
        favorite = movieRepo.getFavoriteById(movie.getId());
    }

    public LiveData<Favorite> getFavorite() {
        return favorite;
    }

    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<ReviewApiResponse> getReviewList() {
        reviewList = movieRepo.getReviews(movie.getValue().getId());
        return reviewList;
    }

    public LiveData<TrailerApiResponse> getTrailerList() {
        trailerList = movieRepo.getTrailers(movie.getValue().getId());
        return trailerList;
    }

    public void addFavorite() {
        final Favorite favorite = new Favorite(movie.getValue().getId(), movie.getValue().getTitle());
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.favoriteDao().insertFavorite(favorite);
            }
        });
    }

    public void removeFavorite() {
        final Favorite favorite = new Favorite(movie.getValue().getId(), movie.getValue().getTitle());
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.favoriteDao().deleteFavorite(favorite);
            }
        });
    }

}
