package br.com.joaoreis.popularmovies.moviedetail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewApiResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public List<Review> reviews;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;

    public final static Parcelable.Creator<ReviewApiResponse> CREATOR = new Creator<ReviewApiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReviewApiResponse createFromParcel(Parcel in) {
            return new ReviewApiResponse(in);
        }

        public ReviewApiResponse[] newArray(int size) {
            return (new ReviewApiResponse[size]);
        }

    };

    protected ReviewApiResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.reviews, (br.com.joaoreis.popularmovies.moviedetail.model.Review.class.getClassLoader()));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ReviewApiResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(page);
        dest.writeList(reviews);
        dest.writeValue(totalPages);
        dest.writeValue(totalResults);
    }

    public int describeContents() {
        return 0;
    }

    public List<Review> getReviews() {
        return reviews;
    }


}