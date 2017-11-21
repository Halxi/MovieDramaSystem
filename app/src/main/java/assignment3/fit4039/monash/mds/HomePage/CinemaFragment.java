package assignment3.fit4039.monash.mds.HomePage;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import assignment3.fit4039.monash.mds.Cinema;
import assignment3.fit4039.monash.mds.CinemaSelectActivity.CinemaAdapter;
import assignment3.fit4039.monash.mds.DatabaseHelper;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

/**
 * Created by jeric on 7/05/2017.
 */

public class CinemaFragment extends Fragment {
    View vCinema;
    private ArrayList<Cinema> cinemaArrayList;
    private DatabaseHelper databaseHelper;
    private CinemaAdapter cinemaAdapter;

    Spinner placeSpinner, labelSpineer, sortSpinner, featureSpinner;
    ListView listView;

    private String places[] = {"All", "Melbourne Central", "Chadstone", "Box Hill", "Glen Waverley", "Dockland"};
    private String labels[] = {"All", "HOYTS", "Kino", "Moonlight", "Village", "Astor"};
    private String sorts[] = {"Nearest", "Highest Rate", "Lowest Price"};
    private String features[] = {"All", "IMAX", "3D", "XtremeScreen", "HOYTS LUX", "Free Parking", "RealD 6FL"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        vCinema = inflater.inflate(R.layout.fragment_cinema, container, false);

        placeSpinner = (Spinner) vCinema.findViewById(R.id.placeSpinner);
        labelSpineer = (Spinner) vCinema.findViewById(R.id.labelSpinner);
        sortSpinner = (Spinner) vCinema.findViewById(R.id.sortSpinner);
        featureSpinner = (Spinner) vCinema.findViewById(R.id.featureSpinner);

        getCinemas(this.getContext());

        ArrayAdapter<String> placeAdatpter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, places);
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

        ArrayAdapter<String> labelAdatpter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, labels);
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

        ArrayAdapter<String> sortAdatpter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sorts);
        sortAdatpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdatpter);


        ArrayAdapter<String> featureAdatpter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, features);
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



        return vCinema;
    }

    /**
     *get  all cinemas from database
     */
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
                listView = (ListView) vCinema.findViewById(R.id.cinemaListView);
                listView.setAdapter(cinemaAdapter);
            }

        }.execute();

    }
}
