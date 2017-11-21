package assignment3.fit4039.monash.mds.CinemaMovieSelect;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import assignment3.fit4039.monash.mds.GoogleCustomerSearch;
import assignment3.fit4039.monash.mds.Movie;
import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 14/05/2017.
 */

public class CinemaMovieAdapter extends PagerAdapter {

    private ArrayList<Movie> movies;
    private int[] image_resources = {R.drawable.guardians_of_the_galaxy_2, R.drawable.king_arthur, R.drawable.need_for_speed_8};
    private Context context;
    private LayoutInflater layoutInflater;

    public CinemaMovieAdapter(Context con, ArrayList<Movie> movieArrayList){
        context = con;
        movies = movieArrayList;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_cinema_movie, container, false);
        ImageView movieImage = (ImageView) view.findViewById(R.id.selected_movie_image);
//        movieImage.setImageBitmap(movies.get(position).getMovieImage());
        movieImage.setImageResource(image_resources[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }


}
