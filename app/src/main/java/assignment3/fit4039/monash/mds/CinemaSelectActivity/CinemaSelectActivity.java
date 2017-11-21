package assignment3.fit4039.monash.mds.CinemaSelectActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import assignment3.fit4039.monash.mds.Cinema;
import assignment3.fit4039.monash.mds.DatabaseHelper;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

public class CinemaSelectActivity extends AppCompatActivity{

    private ArrayList<Cinema> cinemaArrayList;
    private DatabaseHelper databaseHelper;
    private CinemaAdapter cinemaAdapter;

    Spinner placeSpinner, labelSpineer, sortSpinner, featureSpinner;
    ListView listView;

    private String places[] = {"All", "Melbourne Central", "Chadstone", "Box Hill", "Glen Waverley", "Dockland"};
    private String labels[] = {"All", "HOYTS", "Kino", "Moonlight", "Village", "Astor"};
    private String sorts[] = {"Nearest", "Highest Rate", "Lowest Price"};
    private String features[] = {"All", "IMAX", "3D", "XtremeScreen", "HOYTS LUX", "Free Parking", "RealD 6FL"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_select);

        placeSpinner = (Spinner) findViewById(R.id.placeSpinner);
        labelSpineer = (Spinner) findViewById(R.id.labelSpinner);
        sortSpinner = (Spinner) findViewById(R.id.sortSpinner);
        featureSpinner = (Spinner) findViewById(R.id.featureSpinner);

        getCinemas(this);

        ArrayAdapter<String> placeAdatpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        placeAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(placeAdatpter);
        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String place = placeSpinner.getSelectedItem().toString();
                cinemaAdapter.getFilter().filter("place" + place);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> labelAdatpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
        labelAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        labelSpineer.setAdapter(labelAdatpter);
        labelSpineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = labelSpineer.getSelectedItem().toString();
                cinemaAdapter.getFilter().filter("label" + label);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> sortAdatpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sorts);
        sortAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdatpter);


        ArrayAdapter<String> featureAdatpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, features);
        featureAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        featureSpinner.setAdapter(featureAdatpter);
        featureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String feature = featureSpinner.getSelectedItem().toString();
                cinemaAdapter.getFilter().filter("feature" + feature);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void getCinemas(final Context context){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findAllCinema();
            }

            @Override
            protected void onPostExecute(String response) {
                Gson gson = new Gson();
                cinemaArrayList = gson.fromJson(response, new TypeToken<ArrayList<Cinema>>() {
                }.getType());
                cinemaAdapter = new CinemaAdapter(context, cinemaArrayList);
                listView = (ListView) findViewById(R.id.cinemaListView);
                listView.setAdapter(cinemaAdapter);
            }

        }.execute();

    }
}
