package assignment3.fit4039.monash.mds.HomePage;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import assignment3.fit4039.monash.mds.GoogleCustomerSearch;
import assignment3.fit4039.monash.mds.Movie;
import assignment3.fit4039.monash.mds.R;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView movieName, movieRate, movieType, movieDur, movieDesc, movieDescription;
    ImageView movieImage, returnImage;
    View wannaSee, markRate;
    Movie movie;
    boolean checked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("Movie");

        movieName = (TextView) findViewById(R.id.tv_movie_name);
        movieRate = (TextView) findViewById(R.id.tv_movie_score);
        movieType = (TextView) findViewById(R.id.tv_movie_type);
        movieDur = (TextView) findViewById(R.id.tv_src_dur);
        movieDesc = (TextView) findViewById(R.id.tv_pubDesc);
        movieDescription = (TextView) findViewById(R.id.expandText_movie_Desc);
        wannaSee =  findViewById(R.id.wannaSeeView);
        markRate = findViewById(R.id.markRateView);

        movieImage = (ImageView) findViewById(R.id.iv_movie_img);
        returnImage = (ImageView) findViewById(R.id.returnImage);

        if (movie.getMoviename().startsWith("Guardian")){
            movieImage.setImageResource(R.drawable.guardians_of_the_galaxy_2);
        }
        else if (movie.getMoviename().startsWith("King")){
            movieImage.setImageResource(R.drawable.king_arthur);
        }
        else if (movie.getMoviename().startsWith("The Fate")){
            movieImage.setImageResource(R.drawable.need_for_speed_8);
        }
        else if (movie.getMoviename().startsWith("Beauty")){
            movieImage.setImageResource(R.drawable.beauty_and_beast);
        }
        else {
            movieImage.setImageResource(R.drawable.no_image);
        }

        movieName.setText(movie.getMoviename());
        movieRate.setText(String.valueOf(movie.getMovierate()));
        movieType.setText(movie.getMovietag());
        movieDur.setText(movie.getDuration());
        movieDesc.setText(movie.getReleasedate());

        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wannaSee.setOnClickListener(this);
        markRate.setOnClickListener(this);

        getMovieDetail();
    }

    /**
     *get information of this movie from database
     */
    private void getMovieDetail() {
        new AsyncTask<String, Void, String>(){
            @Override
            protected String doInBackground(String... params) {
//                image = GoogleCustomerSearch.ImageSearch(params[0]);
                return GoogleCustomerSearch.GoogleSearh(params[0]);
            }

            @Override
            protected void onPostExecute(String response) {
                if (!response.isEmpty()){
                    movieDescription.setText(response);
                }
                else {
                    movieDescription.setText("No introduction of this movie right now");
                }
//                if (!image.isEmpty()){
//                    movieImage.setImageBitmap(GoogleCustomerSearch.downloadImage(image));
//                }
//                else {
//                    movieImage.setImageDrawable(getResources().getDrawable(R.drawable.no_image));
//                }
            }
        }.execute(movie.getMoviename());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wannaSeeView:
                TextView wannaSeeText = (TextView) findViewById(R.id.wannaSeeText);
//                ImageView wannaSeeImage = (ImageView) findViewById(R.id.wannaSeeImage);
                if (checked){
                    wannaSeeText.setText("Don't Wanna See");
                    wannaSeeText.setTextColor(getResources().getColor(R.color.colorAccent));
//                    wannaSeeImage.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    checked = false;
                }
                else {
                    wannaSeeText.setText("Wanna See");
//                    wannaSeeImage.setBackgroundColor(getResources().getColor(R.color.black));
                    wannaSeeText.setTextColor(getResources().getColor(R.color.white));
                    checked = true;
                }
                break;
            case R.id.markRateView:
                TextView markRateText = (TextView) findViewById(R.id.markRateText);
                markRateText.setText("Marked");
                markRateText.setTextColor(getResources().getColor(R.color.text_yellow));
//                    wannaSeeImage.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                checked = false;
                break;
        }
    }
}
