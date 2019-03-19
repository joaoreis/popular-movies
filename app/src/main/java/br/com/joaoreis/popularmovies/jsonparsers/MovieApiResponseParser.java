package br.com.joaoreis.popularmovies.jsonparsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.joaoreis.popularmovies.model.Movie;
import br.com.joaoreis.popularmovies.model.MovieApiResponse;

public class MovieApiResponseParser {

    public static final String ERROR_PARSING_JSON = "Failed to parse Json";
    public static final String ERROR_INSTANTIATING_UTILS_CLASS = "Utils class, should not be instantiated.";

    private static final String KEY_PAGE = "page";
    private static final String KEY_TOTAL_PAGES = "total_pages";
    private static final String KEY_TOTAL_RESULTS = "total_results";
    private static final String KEY_MOVIES = "results";

    public static MovieApiResponse parse(String json) {

        MovieApiResponse movieApiResponse = new MovieApiResponse();

        try {
            JSONObject responseData = new JSONObject(json);
            Iterator<String> keys = responseData.keys();

            while (keys.hasNext()) {
                String key = keys.next();

                switch (key) {
                    case KEY_PAGE:
                        movieApiResponse.setPage(responseData.getInt(key));
                        break;
                    case KEY_TOTAL_PAGES:
                        movieApiResponse.setTotalPages(responseData.getInt(key));
                        break;
                    case KEY_TOTAL_RESULTS:
                        movieApiResponse.setTotalResults(responseData.getInt(key));
                        break;
                    case KEY_MOVIES:
                        List<Movie> movies = new ArrayList<>();
                        JSONArray jsonArray = responseData.getJSONArray(key);
                        for (int i=0; i< jsonArray.length(); i++) {
                            movies.add((Movie) jsonArray.get(i));
                        }
                        break;
                    default:
                        throw new JSONException(ERROR_PARSING_JSON);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieApiResponse;
    }
}
