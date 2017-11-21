package assignment3.fit4039.monash.mds.CinemaMovieSelect;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import assignment3.fit4039.monash.mds.Cinema;
import assignment3.fit4039.monash.mds.DatabaseHelper;
import assignment3.fit4039.monash.mds.MapActivity;
import assignment3.fit4039.monash.mds.Movie;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;
import assignment3.fit4039.monash.mds.Session;

public class CinemaDetailActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener, View.OnClickListener {
    Cinema cinema;

    ViewPager viewPager;
    CinemaMovieAdapter cinemaMovieAdapter;
    SessionListAdapter sessionListAdapter;
    TextView name, address, tag, movieName, movieTag, movieRate, sessionToday, sessionTomorrow;
    ImageView mapImage;
    ListView sessionList;
    ArrayList<Movie> movies;
    ArrayList<Session> sessions;
    String movieNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        Intent intent = getIntent();
        cinema = intent.getParcelableExtra("Cinema");

        getMovies(this);

        name = (TextView) findViewById(R.id.cinema_detail_name);
        address = (TextView) findViewById(R.id.cinema_detail_address);
        tag = (TextView) findViewById(R.id.cinema_detail_tag);
        movieName = (TextView) findViewById(R.id.selected_movie_name);
        movieTag = (TextView) findViewById(R.id.selected_movie_tag);
        movieRate = (TextView) findViewById(R.id.selected_movie_rate);
        sessionToday = (TextView) findViewById(R.id.selected_movie_today);
        sessionTomorrow = (TextView) findViewById(R.id.selected_movie_tomorrow);

        sessionToday.setOnClickListener(this);
        sessionTomorrow.setOnClickListener(this);

        sessionList = (ListView) findViewById(R.id.sessionListView);

        mapImage = (ImageView) findViewById(R.id.mapImage);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        name.setText(cinema.getCinemaname());
        address.setText(cinema.getCinemaaddress());
        tag.setText(cinema.getCinematag());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        sessionToday.setText("Today " + dayFormat.format(new Date()) + " " + monthFormat.format(new Date()));

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        sessionTomorrow.setText("Tomorrow " + dayFormat.format(dt) + " " + monthFormat.format(dt));

        viewPager.setOnPageChangeListener(this);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newItent = new Intent(CinemaDetailActivity.this, MapActivity.class);
                newItent.putExtra("Cinema", cinema);
                startActivity(newItent);
            }
        });
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Movie movie = movies.get(position);
        movieName.setText(movie.getMoviename());
        movieNameString = movie.getMoviename();
        movieRate.setText(String.valueOf(movie.getMovierate()));
        movieTag.setText(movie.getMovieversion() + " | " + movie.getMovietag().replace(",", " | "));
        getTodaySessions(this);
        sessionToday.setTextColor(getResources().getColor(R.color.colorAccent));
        sessionTomorrow.setTextColor(getResources().getColor(R.color.black));
        sessionListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void getMovies(final Context context){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findAllMovie();
            }

            @Override
            protected void onPostExecute(String response) {
                Gson gson = new Gson();
                movies = gson.fromJson(response, new TypeToken<ArrayList<Movie>>() {
                }.getType());
                Movie m = movies.get(0);
                movieName.setText(m.getMoviename());
                movieTag.setText(m.getMovieversion() + " | " + m.getMovietag().replace(",", " | "));
                movieRate.setText(String.valueOf(m.getMovierate()));
                movieNameString = m.getMoviename();
                getTodaySessions(context);
                cinemaMovieAdapter = new CinemaMovieAdapter(context, movies);
                viewPager.setAdapter(cinemaMovieAdapter);
            }

        }.execute();

    }

    public void getTodaySessions(final Context context){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findAllSession();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                return RestClient.findSession(cinema.getCinemaname(), movieNameString, sdf.format(new Date()));
            }

            @Override
            protected void onPostExecute(String response) {
                Gson gson = new Gson();
                sessions = gson.fromJson(response, new TypeToken<ArrayList<Session>>() {
                }.getType());
                sessionListAdapter = new SessionListAdapter(context, sessions);
                sessionList.setAdapter(sessionListAdapter);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sessionListAdapter.getFilter().filter(cinema.getCinemaname() + "," + movieNameString + "," + sdf.format(new Date()));

            }

        }.execute();

    }

    public void getTomorrowSessions(final Context context){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findAllSession();
//                Date dt = new Date();
//                Calendar c = Calendar.getInstance();
//                c.setTime(dt);
//                c.add(Calendar.DATE, 1);
//                dt = c.getTime();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                return RestClient.findSession(cinema.getCinemaname(), movieNameString, sdf.format(dt));
            }

            @Override
            protected void onPostExecute(String response) {
                Gson gson = new Gson();
                sessions = gson.fromJson(response, new TypeToken<ArrayList<Session>>() {
                }.getType());
                sessionListAdapter = new SessionListAdapter(context, sessions);
                sessionList.setAdapter(sessionListAdapter);
                Date dt = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 1);
                dt = c.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sessionListAdapter.getFilter().filter(cinema.getCinemaname() + "," + movieNameString + "," + sdf.format(dt));

            }

        }.execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selected_movie_today:
                sessionToday.setTextColor(getResources().getColor(R.color.colorAccent));
                sessionTomorrow.setTextColor(getResources().getColor(R.color.black));
                getTodaySessions(this);
                break;
            case R.id.selected_movie_tomorrow:
                sessionToday.setTextColor(getResources().getColor(R.color.black));
                sessionTomorrow.setTextColor(getResources().getColor(R.color.colorAccent));
                getTomorrowSessions(this);
                break;
        }
    }
}
