package assignment3.fit4039.monash.mds.HomePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;

import assignment3.fit4039.monash.mds.MovieSubFragment.DramaSubFragment;
import assignment3.fit4039.monash.mds.MovieSubFragment.MovieSubFragment;
import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 7/05/2017.
 */

public class MovieFragment extends Fragment implements View.OnClickListener {
    View vMovie;
    ViewPager vp_movie;
    TextView tvMovie, tvDrama;
    private FrameLayout.LayoutParams params;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vMovie = inflater.inflate(R.layout.fragment_movie, container, false);
        vp_movie = (ViewPager) vMovie.findViewById(R.id.vp_movie);
        tvMovie = (TextView) vMovie.findViewById(R.id.tv_movie);
        tvDrama = (TextView) vMovie.findViewById(R.id.tv_drama);
        tvMovie.setOnClickListener(this);
        tvDrama.setOnClickListener(this);

        /**
         *create a subfragment inside the fragment
         */
        MovieSubFragment movieSubFragment = new MovieSubFragment();
        DramaSubFragment dramaSubFragment = new DramaSubFragment();
        MovieAdapter movieAdapter = new MovieAdapter(getFragmentManager());
        Fragment[] fragments = new Fragment[]{movieSubFragment, dramaSubFragment};
        String[] titles = new String[]{"Movie", "Drama"};
        movieAdapter.addFragment(Arrays.asList(fragments), Arrays.asList(titles));
        vp_movie.setAdapter(movieAdapter);
        vp_movie.setOffscreenPageLimit(2);

        final int selectedColor = getResources().getColor(R.color.colorAccent);
        final int unselectedColor = getResources().getColor(R.color.white);

        /**
         *switch fragments inside of this fragment
         */
        vp_movie.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        tvMovie.setTextColor(selectedColor);
                        tvMovie.setBackgroundColor(unselectedColor);
                        tvDrama.setTextColor(unselectedColor);
                        tvDrama.setBackgroundColor(selectedColor);
                        break;
                    case 1:
                        tvMovie.setTextColor(unselectedColor);
                        tvMovie.setBackgroundColor(selectedColor);
                        tvDrama.setTextColor(selectedColor);
                        tvDrama.setBackgroundColor(unselectedColor);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tvMovie.setTextColor(selectedColor);
                        tvMovie.setBackgroundColor(unselectedColor);
                        tvDrama.setTextColor(unselectedColor);
                        tvDrama.setBackgroundColor(selectedColor);
                        break;
                    case 1:
                        tvMovie.setTextColor(unselectedColor);
                        tvMovie.setBackgroundColor(selectedColor);
                        tvDrama.setTextColor(selectedColor);
                        tvDrama.setBackgroundColor(unselectedColor);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return vMovie;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_movie:
                vp_movie.setCurrentItem(0);
                break;
            case R.id.tv_drama:
                vp_movie.setCurrentItem(1);
                break;
        }
    }
}
