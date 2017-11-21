package assignment3.fit4039.monash.mds.HomePage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeric on 7/05/2017.
 */

public class MovieAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;


    public MovieAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    public void addFragment(List<Fragment> fragments,List<String> titles){
        mFragments.addAll(fragments);
        mTitles.addAll(titles);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
