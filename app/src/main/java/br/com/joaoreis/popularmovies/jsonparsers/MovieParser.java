package br.com.joaoreis.popularmovies.jsonparsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import br.com.joaoreis.popularmovies.model.Movie;

public class MovieParser {

    public static final String ERROR_PARSING_JSON = "Failed to parse Json";
    public static final String ERROR_INSTANTIATING_UTILS_CLASS = "Utils class, should not be instantiated.";
    private static final String TAG = MovieParser.class.getSimpleName();
    private static final String DATE_FORMAT = "yyyy-mm-dd";
    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185/";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ORIGINAL_TITLE = "original_title";
    private static final String KEY_ORIGINAL_LANGUAGE = "original_language";
    private static final String KEY_RELEASE_DATE = "release_date";
    private static final String KEY_GENRE_IDS = "genre_ids";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_VIDEO = "video";
    private static final String KEY_ADULT = "adult";
    private static final String KEY_VOTE_COUNT = "vote_count";
    private static final String KEY_VOTE_AVERAGE = "vote_average";
    private static final String KEY_POPULARITY = "popularity";

    public MovieParser() {
        throw new IllegalStateException(ERROR_INSTANTIATING_UTILS_CLASS);
    }

    public static Movie parse(String json) {
        JSONObject movieData;
        Movie movie = null;

        try {

            movieData = new JSONObject(json);
            Iterator<String> keys = movieData.keys();
             movie = new Movie();

            while (keys.hasNext()) {
                String key = keys.next();
                switch (key) {
                    case KEY_ID:
                        movie.setId(movieData.getLong(key));
                        break;

                    case KEY_TITLE:
                        movie.setTitle(movieData.getString(key));
                        break;

                    case KEY_ORIGINAL_TITLE:
                        movie.setOriginalTitle(movieData.getString(key));
                        break;

                    case KEY_ORIGINAL_LANGUAGE:
                        movie.setOriginalLanguage(movieData.getString(key));
                        break;

                    case KEY_RELEASE_DATE:
                        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
                        String dateString = movieData.getString(key);
                        Date releaseDate = formatter.parse(dateString);
                        movie.setReleaseDate(releaseDate);
                        break;

                    case KEY_GENRE_IDS:
                        List<Integer> ids = new ArrayList<>();
                        JSONArray idsArray = movieData.getJSONArray(key);
                        for (int i = 0; i < idsArray.length(); i++) {
                            ids.add((Integer) idsArray.get(i));
                        }
                        movie.setGenreIds(ids);
                        break;

                    case KEY_OVERVIEW:
                        movie.setOverview(movieData.getString(key));
                        break;

                    case KEY_POSTER_PATH:
                        movie.setPosterPath(BASE_URL + movieData.getString(key));
                        break;

                    case KEY_BACKDROP_PATH:
                        movie.setBackdropPath(BASE_URL + movieData.getString(key));
                        break;

                    case KEY_VIDEO:
                        movie.setVideo(movieData.getBoolean(key));
                        break;

                    case KEY_ADULT:
                        movie.setAdult(movieData.getBoolean(key));
                        break;

                    case KEY_VOTE_COUNT:
                        movie.setVoteCount(movieData.getLong(key));
                        break;

                    case KEY_VOTE_AVERAGE:
                        movie.setVoteAverage(movieData.getDouble(key));
                        break;

                    case KEY_POPULARITY:
                        movie.setPopularity(movieData.getDouble(key));
                        break;

                    default:
                        throw new JSONException(ERROR_PARSING_JSON);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return movie;
    }
}
