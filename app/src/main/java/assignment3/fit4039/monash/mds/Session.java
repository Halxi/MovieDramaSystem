package assignment3.fit4039.monash.mds;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeric on 15/05/2017.
 */

public class Session implements Parcelable {

    private int sessionid;
    private String starttime;
    private String endtime;
    private String movietype;
    private String moviescreen;
    private int price;
    private Cinema cinema;
    private String sessiondate;
    private Movie movie;


    public Session() {
    }

    public Session(int sessionid, String starttime, String endtime,
                   String movietype, String moviescreen, int price, Cinema cinema, String sessiondate, Movie movie) {
        this.sessionid = sessionid;
        this.starttime = starttime;
        this.endtime = endtime;
        this.movietype = movietype;
        this.moviescreen = moviescreen;
        this.price = price;
        this.cinema = cinema;
        this.sessiondate = sessiondate;
        this.movie = movie;
    }

    protected Session(Parcel in) {
        sessionid = in.readInt();
        starttime = in.readString();
        endtime = in.readString();
        movietype = in.readString();
        moviescreen = in.readString();
        price = in.readInt();
        cinema = in.readParcelable(Cinema.class.getClassLoader());
        sessiondate = in.readString();
        movie = in.readParcelable(Movie.class.getClassLoader());
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getMovietype() {
        return movietype;
    }

    public void setMovietype(String movietype) {
        this.movietype = movietype;
    }

    public String getMoviescreen() {
        return moviescreen;
    }

    public void setMoviescreen(String moviescreen) {
        this.moviescreen = moviescreen;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getSessiondate() {
        return sessiondate;
    }

    public void setSessiondate(String sessiondate) {
        this.sessiondate = sessiondate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sessionid);
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeString(movietype);
        dest.writeString(moviescreen);
        dest.writeInt(price);
        dest.writeParcelable(cinema, flags);
        dest.writeString(sessiondate);
        dest.writeParcelable(movie, flags);
    }
}
