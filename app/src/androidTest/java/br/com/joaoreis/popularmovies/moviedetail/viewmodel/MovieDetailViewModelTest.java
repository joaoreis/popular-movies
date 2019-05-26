package br.com.joaoreis.popularmovies.moviedetail.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.joaoreis.popularmovies.database.Favorite;
import br.com.joaoreis.popularmovies.moviedetail.view.ui.MovieDetailActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailViewModelTest {

    MovieDetailViewModel viewModel;

    @Mock
    MovieDetailActivity activity;

    @Before
    public void setUp() throws Exception {
//        activity = new MovieDetailActivity();
        viewModel = ViewModelProviders.of(activity).get(MovieDetailViewModel.class);
    }

    @Test
    public void getFavorite() {

        LiveData<Movie> favorite = viewModel.getFavorite();
        assertThat(favorite.getValue(), is(viewModel.getFavorite().getValue()));
    }

    @Test
    public void addFavorite() {
//        viewModel.addFavorite();
//        assertThat();
    }
}