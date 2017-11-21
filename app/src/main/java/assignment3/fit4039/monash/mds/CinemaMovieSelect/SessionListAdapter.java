package assignment3.fit4039.monash.mds.CinemaMovieSelect;

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

import assignment3.fit4039.monash.mds.R;
import assignment3.fit4039.monash.mds.SeatSelect.SeatSelectActivity;
import assignment3.fit4039.monash.mds.Session;

/**
 * Created by jeric on 18/05/2017.
 */

public class SessionListAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private ArrayList<Session> sessions;
    private ArrayList<Session> resultList;

    SessionListAdapter(Context con, ArrayList<Session> sessionList){
        context = con;
        sessions = sessionList;
        resultList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return sessions.size();
    }

    @Override
    public Object getItem(int position) {
        return sessions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class SessionHolder{
        TextView startTime;
        TextView endTime;
        TextView movieType;
        TextView movieScreen;
        TextView price;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final SessionHolder sessionHolder;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_session, viewGroup, false);
            sessionHolder = new SessionHolder();
            sessionHolder.startTime = (TextView) view.findViewById(R.id.startTime);
            sessionHolder.endTime = (TextView) view.findViewById(R.id.endTime);
            sessionHolder.movieType = (TextView) view.findViewById(R.id.movieType);
            sessionHolder.movieScreen = (TextView) view.findViewById(R.id.movieScreen);
            sessionHolder.price = (TextView) view.findViewById(R.id.sessionPrice);
            view.setTag(sessionHolder);
        }
        else{
            sessionHolder = (SessionHolder) view.getTag();
        }

        final Session session = sessions.get(i);
        sessionHolder.startTime.setText(session.getStarttime());
        sessionHolder.endTime.setText(session.getEndtime());
        sessionHolder.movieType.setText(session.getMovietype());
        sessionHolder.movieScreen.setText(session.getMoviescreen());
        sessionHolder.price.setText(String.valueOf(session.getPrice()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SeatSelectActivity.class);
                intent.putExtra("Session", session);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                final ArrayList<Session> results = new ArrayList<>();
                String[] parts = constraint.toString().split(",");
                String cinemaName = parts[0];
                String movieName = parts[1];
                String sessionDate = parts[2];
                if (resultList.isEmpty()){
                    resultList = sessions;
                }
                if (constraint != null){
                    if (!resultList.isEmpty()){
                        for (Session s: resultList){
                            if (s.getCinema().getCinemaname().equals(cinemaName) &&
                                    s.getMovie().getMoviename().equals(movieName) &&
                                    s.getSessiondate().equals(sessionDate)){
                                results.add(s);
                            }
                        }
                    }
                    filterResults.values = results;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                sessions = (ArrayList<Session>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
