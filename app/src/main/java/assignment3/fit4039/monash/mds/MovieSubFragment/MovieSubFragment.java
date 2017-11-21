package assignment3.fit4039.monash.mds.MovieSubFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import assignment3.fit4039.monash.mds.Movie;
import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.RestClient;

/**
 * Created by jeric on 7/05/2017.
 */

public class MovieSubFragment extends Fragment implements View.OnClickListener{

    View vMovie;
    EditText editText;
    ListView listView;
    ArrayList<Movie> movieArrayList;
    private MovieListAdapter movieListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vMovie = inflater.inflate(R.layout.subfragment_movie, container, false);
        listView = (ListView) vMovie.findViewById(R.id.movieListView);
        editText = (EditText) vMovie.findViewById(R.id.searchEditText);
        editText.setHint("Search Movie");
        editText.setOnClickListener(this);
        getMovies();
        return vMovie;
    }

    /**
     * get all movies
     */
    public void getMovies(){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return RestClient.findAllMovie();
            }

            @Override
            protected void onPostExecute(String response) {
                Gson gson = new Gson();
                movieArrayList = gson.fromJson(response, new TypeToken<ArrayList<Movie>>() {
                }.getType());
                movieListAdapter = new MovieListAdapter(getContext(), movieArrayList);
                listView.setAdapter(movieListAdapter);
            }

        }.execute();

    }

    @Override
    public void onClick(View v) {
        movieListAdapter.getFilter().filter(editText.getText().toString());
    }
}
