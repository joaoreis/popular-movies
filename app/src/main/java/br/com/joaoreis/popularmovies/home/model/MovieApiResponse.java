package br.com.joaoreis.popularmovies.home.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//TODO: abstract to return only the movie list
public class MovieApiResponse {

    @Expose
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("results")
    private List<Movie> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    @NonNull
    public String toString() {

        return "Page: " + page + "\n" +
                "Total Pages: " + totalPages + "\n" +
                "Total Results: " + totalResults + "\n" +
                "Movies: " + movies;
    }
}

//{"page":1,"total_results":19797,"total_pages":990,"trailers":