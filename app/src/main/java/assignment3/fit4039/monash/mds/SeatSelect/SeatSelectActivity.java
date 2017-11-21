package assignment3.fit4039.monash.mds.SeatSelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import assignment3.fit4039.monash.mds.ConfirmOrderActivity;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.Session;

public class SeatSelectActivity extends AppCompatActivity {
    public SeatTable seatTableView;

    TextView movieName, sessionDate, movieVersion, displayText;
    Button button;
    Session session;

    ArrayList<Seat> seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seat_select);

        final Intent intent = getIntent();
        session = intent.getParcelableExtra("Session");

        seats = new ArrayList<>();

        movieName = (TextView) findViewById(R.id.seat_select_movieName);
        sessionDate = (TextView) findViewById(R.id.seat_select_time);
        movieVersion = (TextView) findViewById(R.id.seat_select_movieVersion);
        displayText = (TextView) findViewById(R.id.seat_select_display);
        button = (Button) findViewById(R.id.seat_select_button);

        movieName.setText(session.getMovie().getMoviename());
        sessionDate.setText(session.getSessiondate());
        movieVersion.setText(session.getMovie().getMovieversion());

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName(session.getMoviescreen());
        seatTableView.setMaxSelected(3);

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                Seat seat = new Seat(row, column);
                seats.add(seat);
                button.setBackgroundColor(getResources().getColor(R.color.button_orange));
                button.setClickable(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newIntent = new Intent(SeatSelectActivity.this, ConfirmOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Session", session);
                        bundle.putParcelableArrayList("Seats", seats);
                        newIntent.putExtra("Bundle", bundle);
                        startActivity(newIntent);
                    }
                });
            }

            @Override
            public void unCheck(int row, int column) {
                Iterator<Seat> iterator = seats.iterator();
                while (iterator.hasNext()){
                    Seat seat = iterator.next();
                    if (seat.getRow() == row && seat.getCloumn() == column){
                        iterator.remove();
                    }
                }
                if (seats.size() == 0) {
                    button.setBackgroundColor(getResources().getColor(R.color.text_yellow));
                    button.setClickable(false);
                }
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);

    }

}
