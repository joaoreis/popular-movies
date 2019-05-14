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

    public MovieDetailViewModel(Application application) {
        super(application);
        movieRepo = new MovieRepository();
        movie = new MutableLiveData<>();
        reviewList = new MutableLiveData<>();
        trailerList = new MutableLiveData<>();

        database = AppDatabase.getInstance(application);
        favorite = new MutableLiveData<>();
    }

    @Inject
    public MovieDetailViewModel(Application application, MovieRepository movieRepo) {
        super(application);
        this.movieRepo = movieRepo;
        movie = new MutableLiveData<>();
        reviewList = new MutableLiveData<>();
        trailerList = new MutableLiveData<>();

        database = AppDatabase.getInstance(application);
        favorite = new MutableLiveData<>();
        //TODO:remember to delete
        database.favoriteDao().deleteAllFavorites();
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

    private LiveData<Favorite> getFavorite() {
        final long id = movie.getValue().getId();

        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                favorite = database.favoriteDao().getFavoriteById(id);
            }
        });

        return favorite;
    }

    public LiveData<Boolean> isMovieFavorite() {
        LiveData<Boolean> isFavorite = new MutableLiveData<>();
        ((MutableLiveData<Boolean>) isFavorite).setValue(getFavorite().getValue() != null);
        return isFavorite;
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
