package br.com.joaoreis.popularmovies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY id")
    LiveData<List<Favorite>> getAllFavorites();

    @Query("SELECT * FROM favorites WHERE id = :id")
    LiveData<Favorite> getFavoriteById(long id);

    @Insert
    void insertFavorite(Favorite favorite);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Query("DELETE FROM favorites")
    void deleteAllFavorites();
}
