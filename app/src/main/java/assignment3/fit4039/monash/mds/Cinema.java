package assignment3.fit4039.monash.mds;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by jeric on 8/05/2017.
 */

public class Cinema implements Parcelable{

    public static final String TABLE_NAME = "cinemaDB";
    public static final String COLUMN_CINEMANAME = "cinemaname";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_ADDRESS = "cinemaaddress";
    public static final String COLUMN_CINEMATAG = "cinematag";

    private String cinemaname;
    private int price;
    private String cinemaaddress;
    private String cinematag;

    public Cinema(String cinemaname, int price, String cinemaaddress, String cinematag) {
        this.cinemaname = cinemaname;
        this.price = price;
        this.cinemaaddress = cinemaaddress;
        this.cinematag = cinematag;
    }

    public static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "(" +
            COLUMN_CINEMANAME + " STRING PRIMARY KEY NOT NULL, " +
            COLUMN_PRICE + " INTEGER NOT NULL, " +
            COLUMN_ADDRESS + " STRING NOT NULL, " +
            COLUMN_CINEMATAG + " STRING NOT NULL " +
            ")";

    protected Cinema(Parcel in) {
        cinemaname = in.readString();
        price = in.readInt();
        cinemaaddress = in.readString();
        cinematag = in.readString();
    }

    public static final Creator<Cinema> CREATOR = new Creator<Cinema>() {
        @Override
        public Cinema createFromParcel(Parcel in) {
            return new Cinema(in);
        }

        @Override
        public Cinema[] newArray(int size) {
            return new Cinema[size];
        }
    };

    public String getCinemaname() {
        return cinemaname;
    }

    public void setCinemaname(String cinemaname) {
        this.cinemaname = cinemaname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCinemaaddress() {
        return cinemaaddress;
    }

    public void setCinemaaddress(String cinemaaddress) {
        this.cinemaaddress = cinemaaddress;
    }

    public String getCinematag() {
        return cinematag;
    }

    public void setCinematag(String cinematag) {
        this.cinematag = cinematag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cinemaname);
        dest.writeInt(price);
        dest.writeString(cinemaaddress);
        dest.writeString(cinematag);
    }
}
