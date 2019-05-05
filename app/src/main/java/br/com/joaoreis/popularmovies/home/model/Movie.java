package br.com.joaoreis.popularmovies.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Movie implements Parcelable {
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

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    protected Movie(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        originalTitle = in.readString();
        originalLanguage = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        video = in.readByte() != 0;
        adult = in.readByte() != 0;
        voteCount = in.readLong();
        voteAverage = in.readDouble();
        popularity = in.readDouble();
        releaseDate = new Date(in.readLong());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeLong(voteCount);
        dest.writeDouble(voteAverage);
        dest.writeDouble(popularity);
        dest.writeLong(releaseDate.getTime());
    }
}