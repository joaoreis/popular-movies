package br.com.joaoreis.popularmovies.moviedetail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerApiResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("results")
    @Expose
    public List<Trailer> trailers;
    public final static Parcelable.Creator<TrailerApiResponse> CREATOR = new Creator<TrailerApiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TrailerApiResponse createFromParcel(Parcel in) {
            return new TrailerApiResponse(in);
        }

        public TrailerApiResponse[] newArray(int size) {
            return (new TrailerApiResponse[size]);
        }

    }
            ;

    protected TrailerApiResponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.trailers, (Trailer.class.getClassLoader()));
    }

    public TrailerApiResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(trailers);
    }

    public int describeContents() {
        return 0;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}