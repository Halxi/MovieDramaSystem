package assignment3.fit4039.monash.mds;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeric on 8/06/2017.
 */

public class Client implements Parcelable {

    private int clientid;
    private String fname;
    private String lname;
    private String dob;
    private String gender;
    private String email;
    private String password;

    public Client() {
    }

    public Client(int clientid, String fname, String lname, String dob, String gender, String email, String password) {
        this.clientid = clientid;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    protected Client(Parcel in) {
        clientid = in.readInt();
        fname = in.readString();
        lname = in.readString();
        dob = in.readString();
        gender = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(clientid);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(password);
    }
}
