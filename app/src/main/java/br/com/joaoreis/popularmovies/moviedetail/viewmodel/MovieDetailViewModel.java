package br.com.joaoreis.popularmovies.moviedetail.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.com.joaoreis.popularmovies.home.model.Movie;
import br.com.joaoreis.popularmovies.home.repository.MovieRepository;
import br.com.joaoreis.popularmovies.moviedetail.model.ReviewApiResponse;
import br.com.joaoreis.popularmovies.moviedetail.model.TrailerApiResponse;

public class MovieDetailViewModel extends ViewModel {

    private final MovieRepository movieRepo;
    private MutableLiveData<Movie> movie;
    private LiveData<ReviewApiResponse> reviewList;
    private LiveData<TrailerApiResponse> trailerList;

    public MovieDetailViewModel() {
        movieRepo = new MovieRepository();
        movie = new MutableLiveData<>();
        reviewList = new MutableLiveData<>();
        trailerList = new MutableLiveData<>();
    }

    @Inject
    public MovieDetailViewModel(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
        movie = new MutableLiveData<>();
        reviewList = new MutableLiveData<>();
        trailerList = new MutableLiveData<>();
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


}
