package assignment3.fit4039.monash.mds.HomePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 7/05/2017.
 */

public class DiscoverFragment extends Fragment {
    View vDiscover;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        vDiscover = inflater.inflate(R.layout.fragment_discover, container, false);

        return vDiscover;
    }
}
