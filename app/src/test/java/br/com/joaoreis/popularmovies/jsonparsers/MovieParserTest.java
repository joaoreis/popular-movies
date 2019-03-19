package br.com.joaoreis.popularmovies.jsonparsers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.joaoreis.popularmovies.model.Movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class MovieParserTest {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185/";;
    private static final String DATE_FORMAT = "yyyy-mm-dd";
    private static final String jsonMovieString = "{\"vote_count\":686,\"id\":299537,\"video\":false," +
            "\"vote_average\":7.2,\"title\":\"Captain Marvel\",\"popularity\":424.292," +
            "\"poster_path\":\"\\/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg\",\"original_language\":" +
            "\"en\",\"original_title\":\"Captain Marvel\",\"genre_ids\":[28,12,878]," +
            "\"backdrop_path\":\"\\/d1hQaeKeAW3FBc3v6tIP5utleU0.jpg\",\"adult\":false," +
            "\"overview\":\"The story follows Carol Danvers as she becomes one of the " +
            "universe’s most powerful heroes when Earth is caught in the middle of a galactic " +
            "war between two alien races. Set in the 1990s, Captain Marvel is an all-new a" +
            "dventure from a previously unseen period in the history of the Marvel Cinematic " +
            "Universe.\",\"release_date\":\"2019-03-06\"}";;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testsIntantiatingThrowsException() {
        exception.expect(IllegalStateException.class);
        MovieParser parser = new MovieParser();
    }

    @Test
    public void testParsewithDummyJson() throws ParseException {
        Movie movie = MovieParser.parse(jsonMovieString);
        assertNotNull(movie);
        assertEquals(686,movie.getVoteCount());
        assertEquals(299537,movie.getId().longValue());
        assertFalse(movie.isVideo());
        assertEquals(7.2,movie.getVoteAverage(),0.01);
        assertEquals("Captain Marvel", movie.getTitle());
        assertEquals(424.292, movie.getPopularity(), 0.0001);
        assertEquals(BASE_URL + "/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg", movie.getPosterPath() );
        assertEquals("en",movie.getOriginalLanguage());
        assertEquals("Captain Marvel", movie.getOriginalTitle());

        List<Integer> expected = Arrays.asList(28,12,878);
        assertEquals(expected,movie.getGenreIds());
        assertEquals(BASE_URL + "/d1hQaeKeAW3FBc3v6tIP5utleU0.jpg", movie.getBackdropPath() );
        assertFalse(movie.isAdult());
        assertEquals("The story follows Carol Danvers as she becomes one of the " +
                "universe’s most powerful heroes when Earth is caught in the middle of a galactic " +
                "war between two alien races. Set in the 1990s, Captain Marvel is an all-new a" +
                "dventure from a previously unseen period in the history of the Marvel Cinematic " +
                "Universe.", movie.getOverview());
        Date date = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse("2019-03-06");
        assertEquals(date, movie.getReleaseDate());

    }
}