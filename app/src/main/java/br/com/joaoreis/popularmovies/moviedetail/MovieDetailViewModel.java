package br.com.joaoreis.popularmovies.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import br.com.joaoreis.popularmovies.home.model.Movie;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<Movie> movie;

    public MovieDetailViewModel() {
        movie = new MutableLiveData<>();
    }

    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }
}
