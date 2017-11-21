package assignment3.fit4039.monash.mds.MovieSubFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import assignment3.fit4039.monash.mds.R;

/**
 * Created by jeric on 7/05/2017.
 */

public class DramaSubFragment extends Fragment {

    View Drama;
    TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        Drama = inflater.inflate(R.layout.subfragment_drama, container, false);


        return Drama;
    }
}
