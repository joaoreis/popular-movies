package br.com.joaoreis.popularmovies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.joaoreis.popularmovies.home.model.Movie;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY id")
    LiveData<List<Movie>> getAllFavorites();

    @Query("SELECT * FROM favorites WHERE id = :id")
    LiveData<Movie> getFavoriteById(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorite(Movie favorite);

    @Delete
    void deleteFavorite(Movie favorite);

    @Query("DELETE FROM favorites")
    void deleteAllFavorites();
}
