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
    public List<Trailer> trailers = null;
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

}

/*{"id":550,"trailers":[{"id":"5c9294240e0a267cd516835f","iso_639_1":"en","iso_3166_1":"US",
"key":"BdJKm16Co6M","name":"Fight Club | #TBT Trailer | 20th Century FOX","site":"YouTube",
"size":1080,"type":"Trailer"}]}
*
* {"id":550,"page":1,"trailers":[{"author":"Goddard","content":"Pretty awesome movie.  It shows what one crazy person can convince other crazy people to do.
  * Everyone needs something to believe in.  I recommend Jesus Christ, but they want Tyler Durden.","
  * id":"5b1c13b9c3a36848f2026384","url":"https://www.themoviedb.org/review/5b1c13b9c3a36848f2026384"},
  * {"author":"Brett Pascoe","content":"In my top 5 of all time favourite movies. Great story line and a movie you can watch over and over again.",
  * "id":"5b3e1ba1925141144c007f17","url":"https://www.themoviedb.org/review/5b3e1ba1925141144c007f17"}],"total_pages":1,"total_results":2}
* */