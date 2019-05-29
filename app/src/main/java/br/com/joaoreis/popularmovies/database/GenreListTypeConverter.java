package br.com.joaoreis.popularmovies.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class GenreListTypeConverter {


    @TypeConverter
    public static List<Integer> toGenreIdList(String value) {
        if (value == null) {
            return null;
        }

        List<Integer> genreIdList = new ArrayList<>();
        String[] splitted = value.split(";");
        for (String id : splitted) {
            genreIdList.add(Integer.parseInt(id));
        }
        return genreIdList;
    }

    @TypeConverter
    public static String toString(List<Integer> value) {
        if (value == null) {
            return null;
        }
        StringBuilder genreIdString = new StringBuilder();
        for (Integer genre : value) {
         genreIdString.append(genre.toString() + ";");
        }
        return genreIdString.toString();
    }

}
