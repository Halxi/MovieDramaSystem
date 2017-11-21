package assignment3.fit4039.monash.mds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import assignment3.fit4039.monash.mds.HomePage.MainActivity;
import assignment3.fit4039.monash.mds.SeatSelect.Seat;

public class ConfirmOrderActivity extends AppCompatActivity {

    TextView movieName, sessionDate, movieVersion, cinemaName, sessionScreen, seats,
    totalPrice;

    Button confirmPaymentButton;

    String seatString = "";
    int countPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        Session session = bundle.getParcelable("Session");
        ArrayList<Seat> seatArrayList = bundle.getParcelableArrayList("Seats");

        movieName = (TextView) findViewById(R.id.confirm_movieName);
        sessionDate = (TextView) findViewById(R.id.confirm_date);
        movieVersion = (TextView) findViewById(R.id.confirm_movieVersion);
        cinemaName = (TextView) findViewById(R.id.confirm_cinema);
        sessionScreen = (TextView) findViewById(R.id.confirm_screen);
        seats = (TextView) findViewById(R.id.confirm_seat);
        totalPrice = (TextView) findViewById(R.id.confirm_price);

        confirmPaymentButton = (Button) findViewById(R.id.confirm_pay_button);

        movieName.setText(session.getMovie().getMoviename());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(session.getSessiondate());
            if (date.equals(new Date())) {
                sessionDate.setText("Today " + dayFormat.format(date) + " " + monthFormat.format(date));
            }
            else{
                sessionDate.setText("Tomorrow " + dayFormat.format(date) + " " + monthFormat.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        movieVersion.setText("(" + session.getMovie().getMovieversion() + ")");
        cinemaName.setText(session.getCinema().getCinemaname());
        sessionScreen.setText(session.getMoviescreen());
        for (Seat seat: seatArrayList){
            seatString += "Row" + seat.getRow() + "Seat" + seat.getCloumn() + " ";
            countPrice += 1;
        }
        seats.setText(seatString);
        totalPrice.setText("$" + session.getPrice()*countPrice);

        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToast("Payment Succeed");
                Intent newIntent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });
    }

    protected void sendToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
