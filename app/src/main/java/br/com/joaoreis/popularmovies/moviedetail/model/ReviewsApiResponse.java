package br.com.joaoreis.popularmovies.moviedetail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsApiResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("reviews")
    @Expose
    public List<Review> reviews = null;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    public final static Parcelable.Creator<ReviewsApiResponse> CREATOR = new Creator<ReviewsApiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReviewsApiResponse createFromParcel(Parcel in) {
            return new ReviewsApiResponse(in);
        }

        public ReviewsApiResponse[] newArray(int size) {
            return (new ReviewsApiResponse[size]);
        }

    };

    protected ReviewsApiResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.reviews, (br.com.joaoreis.popularmovies.moviedetail.model.Review.class.getClassLoader()));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ReviewsApiResponse() {
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

}