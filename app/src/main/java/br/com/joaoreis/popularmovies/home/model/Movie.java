package br.com.joaoreis.popularmovies.home.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {
    @Expose
    private Long id;

    @Expose
    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("release_date")
    private Date releaseDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @Expose
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @Expose
    private boolean video;

    @Expose
    private boolean adult;

    @SerializedName("vote_count")
    private long voteCount;

    @SerializedName("vote_average")
    private double voteAverage;

    @Expose
    private double popularity;

    //
//{\"vote_count\":686,\"id\":299537,\"video\":false," +
//        "\"vote_average\":7.2,\"title\":\"Captain Marvel\",\"popularity\":424.292," +
//        "\"poster_path\":\"\\/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg\",\"original_language\":" +
//        "\"en\",\"original_title\":\"Captain Marvel\",\"genre_ids\":[28,12,878]," +
//        "\"backdrop_path\":\"\\/d1hQaeKeAW3FBc3v6tIP5utleU0.jpg\",\"adult\":false," +
//        "\"overview\":\"The story follows Carol Danvers as she becomes one of the " +
//        "universe’s most powerful heroes when Earth is caught in the middle of a galactic " +
//        "war between two alien races. Set in the 1990s, Captain Marvel is an all-new a" +
//        "dventure from a previously unseen period in the history of the Marvel Cinematic " +
//        "Universe.\",\"release_date\":\"2019-03-06\"}";;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}