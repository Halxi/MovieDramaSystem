package assignment3.fit4039.monash.mds.CinemaSelectActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import assignment3.fit4039.monash.mds.Cinema;
import assignment3.fit4039.monash.mds.CinemaMovieSelect.CinemaDetailActivity;
import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 7/05/2017.
 */

public class CinemaAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private ArrayList<Cinema> cinemas;
    private ArrayList<Cinema> resultList;

    public CinemaAdapter(Context con, ArrayList<Cinema> cinemaArrayList){
        context = con;
        cinemas = cinemaArrayList;
        resultList = cinemas;
    }

    @Override
    public int getCount() {
        return cinemas.size();
    }

    @Override
    public Object getItem(int position) {
        return cinemas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                final ArrayList<Cinema> results = new ArrayList<>();
                if (constraint.toString().contains("All")){
                    filterResults.values = resultList;
                }
                else {
                    if (constraint != null) {
                            for (final Cinema c : resultList) {
                                if (constraint.toString().startsWith("place")) {
                                    String place = constraint.toString().substring(5);
                                    if (c.getCinemaaddress().toLowerCase().contains(place.toLowerCase())) {
                                        results.add(c);
                                    }
                                } else if (constraint.toString().startsWith("label")) {
                                    if (c.getCinemaaddress().toLowerCase().contains(constraint.toString().substring(5).toLowerCase())) {
                                        results.add(c);
                                    }
                                }
//                            else if (constraint.toString().startsWith("sort")){
//                                if (c.getCinemaAddress().toLowerCase().contains(constraint.toString().substring(4).toLowerCase())) {
//                                    results.add(c);
//                                }
//                            }
                                else if (constraint.toString().startsWith("feature")) {
                                    if (c.getCinematag().toLowerCase().contains(constraint.toString().substring(7).toLowerCase())) {
                                        results.add(c);
                                    }
                                }
                            }

                        filterResults.values = results;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                cinemas = (ArrayList<Cinema>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CinemaHolder{
        TextView cinemaName;
        TextView price;
        TextView address;
        TextView cinemaTag;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final CinemaHolder cinemaHolder;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_cinema, viewGroup, false);
            cinemaHolder = new CinemaHolder();
            cinemaHolder.cinemaName = (TextView) view.findViewById(R.id.list_cinema_name);
            cinemaHolder.price = (TextView) view.findViewById(R.id.list_cinema_price);
            cinemaHolder.address = (TextView) view.findViewById(R.id.addressText);
            cinemaHolder.cinemaTag = (TextView) view.findViewById(R.id.tagText);
            view.setTag(cinemaHolder);
        }
        else {
            cinemaHolder = (CinemaHolder) view.getTag();
        }

        final Cinema cinema = cinemas.get(i);
        cinemaHolder.cinemaName.setText(cinema.getCinemaname());
        cinemaHolder.price.setText(String.valueOf(cinema.getPrice()));
        cinemaHolder.address.setText(cinema.getCinemaaddress());
        cinemaHolder.cinemaTag.setText(cinema.getCinematag());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CinemaDetailActivity.class);
                intent.putExtra("Cinema", cinema);
                context.startActivity(intent);
            }
        });


        return view;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }
}
