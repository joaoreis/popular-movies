package br.com.joaoreis.popularmovies.moviedetail.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.joaoreis.popularmovies.home.model.Movie;

/**
 * Created by João Reis on 20/05/19.
 */



public class MovieDetailViewModelFactory implements ViewModelProvider.Factory {
    private final Movie movie;
    private final Application application;


    public MovieDetailViewModelFactory(Application application, Movie movie) {
        this.movie = movie;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDetailViewModel(application, movie);
    }
}