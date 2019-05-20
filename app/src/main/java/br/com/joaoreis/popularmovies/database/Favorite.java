package br.com.joaoreis.popularmovies.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long movieId;

    @ColumnInfo(name = "movie_title")
    private String movieTitle;


    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }


    public Favorite(long movieId, String movieTitle) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }


}
