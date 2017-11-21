package assignment3.fit4039.monash.mds.SeatSelect;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeric on 21/05/2017.
 */

public class Seat implements Parcelable {

    private int row;
    private int cloumn;

    public Seat() {
    }

    public Seat(int row, int cloumn) {
        this.row = row;
        this.cloumn = cloumn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCloumn() {
        return cloumn;
    }

    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }

    protected Seat(Parcel in) {
        row = in.readInt();
        cloumn = in.readInt();
    }

    public static final Creator<Seat> CREATOR = new Creator<Seat>() {
        @Override
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }

        @Override
        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(row);
        dest.writeInt(cloumn);
    }
}
