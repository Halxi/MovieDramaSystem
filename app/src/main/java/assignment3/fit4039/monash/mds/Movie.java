package assignment3.fit4039.monash.mds;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeric on 7/05/2017.
 */

public class Movie implements Parcelable {

    public static final String TABLE_NAME = "movieDB";
    public static final String COLUMN_NAME = "movieName";
    public static final String COLUMN_VERSION = "movieVersion";
    public static final String COLUMN_RATE = "movieRate";
    public static final String COLUMN_SHORTINTRO = "shortIntro";
    public static final String COLUMN_TAG = "movietag";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_RELEASEDATE = "releasedate";


    private String moviename;
    private String movieversion;
    private double movierate;
    private String shortintro;
    private String movietag;
    private String duration;
    private String releasedate;
    private Bitmap movieImage;

    public static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "(" +
            COLUMN_NAME + " STRING PRIMARY KEY  NOT NULL, " +
            COLUMN_VERSION + " STRING NOT NULL, " +
            COLUMN_RATE + " DOUBLE NOT NULL, " +
            COLUMN_SHORTINTRO + " STRING NOT NULL, " +
            COLUMN_TAG + " STRING NOT NULL, " +
            COLUMN_DURATION + " STRING NOT NULL, " +
            COLUMN_RELEASEDATE + " STRING NOT NULL" +
            ")";

    public Movie() {
    }

    public Movie(String moviename, String movieversion, double movierate, String shortintro, String movietag, String duration, String releasedate) {
        this.moviename = moviename;
        this.movieversion = movieversion;
        this.movierate = movierate;
        this.shortintro = shortintro;
        this.movietag = movietag;
        this.duration = duration;
        this.releasedate = releasedate;
    }

    protected Movie(Parcel in) {
        moviename = in.readString();
        movieversion = in.readString();
        movierate = in.readDouble();
        shortintro = in.readString();
        movietag = in.readString();
        duration = in.readString();
        releasedate = in.readString();
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

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovieversion() {
        return movieversion;
    }

    public void setMovieversion(String movieversion) {
        this.movieversion = movieversion;
    }

    public double getMovierate() {
        return movierate;
    }

    public void setMovierate(double movierate) {
        this.movierate = movierate;
    }

    public String getShortintro() {
        return shortintro;
    }

    public void setShortintro(String shortintro) {
        this.shortintro = shortintro;
    }

    public String getMovietag() {
        return movietag;
    }

    public void setMovietag(String movietag) {
        this.movietag = movietag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public Bitmap getMovieImage() {
        return movieImage;
    }

    public void setMovieImage() {
        new AsyncTask<String, Void, String>(){
            @Override
            protected String doInBackground(String... params) {
                return GoogleCustomerSearch.ImageSearch(params[0]);
            }

            @Override
            protected void onPostExecute(String response) {
                if (response != null){
                    movieImage = GoogleCustomerSearch.downloadImage(response);
                }
            }
        }.execute(moviename);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moviename);
        dest.writeString(movieversion);
        dest.writeDouble(movierate);
        dest.writeString(shortintro);
        dest.writeString(movietag);
        dest.writeString(duration);
        dest.writeString(releasedate);
    }
}
