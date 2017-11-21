package assignment3.fit4039.monash.mds.MovieSubFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import assignment3.fit4039.monash.mds.CinemaSelectActivity.CinemaSelectActivity;
import assignment3.fit4039.monash.mds.Movie;
import assignment3.fit4039.monash.mds.HomePage.MovieDetailActivity;
import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 7/05/2017.
 */

public class MovieListAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> resultList;

    MovieListAdapter(Context con, ArrayList<Movie> movieArrayList){
        context = con;
        movieList = movieArrayList;
        resultList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *filter movies when searching movies
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                final ArrayList<Movie> results = new ArrayList<>();
                if (resultList.isEmpty()){
                    resultList = movieList;
                }
                if (constraint != null) {
                    if (!resultList.isEmpty()) {
                        for (final Movie c : resultList) {
                            if (c.getMoviename().contains(constraint)) {
                                results.add(c);
                            }
                        }
                        filterResults.values = results;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                movieList = (ArrayList<Movie>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MovieHolder{
        TextView movieName;
        TextView rate;
        TextView shortIntro;
        ImageView movieImage;
        ImageView movieVersion;
        TextView buyTicket;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MovieHolder movieHolder;


        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_movie, viewGroup, false);
            movieHolder = new MovieHolder();
            movieHolder.movieName = (TextView) view.findViewById(R.id.tv_movie_name);
            movieHolder.rate = (TextView) view.findViewById(R.id.tv_movie_rate);
            movieHolder.shortIntro = (TextView) view.findViewById(R.id.tv_movie_shortIntro);
            movieHolder.movieImage = (ImageView) view.findViewById(R.id.iv_movie_img);
            movieHolder.movieVersion = (ImageView) view.findViewById(R.id.iv_ver);
            movieHolder.buyTicket = (TextView) view.findViewById(R.id.tv_buy_ticket);
            view.setTag(movieHolder);
        }
        else {
            movieHolder = (MovieHolder) view.getTag();
        }

        final Movie movie = movieList.get(i);
//        movie.setMovieImage();
        movieHolder.movieName.setText(movie.getMoviename());
        movieHolder.rate.setText("Rate " + movie.getMovierate());
        movieHolder.shortIntro.setText(movie.getShortintro());
        if (movie.getMoviename().startsWith("Guardian")){
            movieHolder.movieImage.setImageResource(R.drawable.guardians_of_the_galaxy_2);
        }
        else if (movie.getMoviename().startsWith("King")){
            movieHolder.movieImage.setImageResource(R.drawable.king_arthur);
        }
        else if (movie.getMoviename().startsWith("The Fate")){
            movieHolder.movieImage.setImageResource(R.drawable.need_for_speed_8);
        }
        else if (movie.getMoviename().startsWith("Beauty")){
            movieHolder.movieImage.setImageResource(R.drawable.beauty_and_beast);
        }
        else {
            movieHolder.movieImage.setImageResource(R.drawable.no_image);
        }

        if (movie.getMovieversion().contains("IMAX")){
            movieHolder.movieVersion.setImageResource(R.drawable.ic_3d_imax);
        }
        else if (movie.getMovieversion().contains("3D")){
            movieHolder.movieVersion.setImageResource(R.drawable.ic_3d);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("Movie", movie);
                context.startActivity(intent);
            }
        });

        movieHolder.buyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CinemaSelectActivity.class);
                intent.putExtra("Movie", movie);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }


}
