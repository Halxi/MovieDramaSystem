package assignment3.fit4039.monash.mds.HomePage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import assignment3.fit4039.monash.mds.R;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg_main;

    private MovieFragment movieFragment;
    private CinemaFragment cinemaFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRg();

        movieFragment = new MovieFragment();
        cinemaFragment = new CinemaFragment();
        discoverFragment = new DiscoverFragment();
        mineFragment = new MineFragment();
//        if (getIntent().hasExtra("Email")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fl_container_main, new MineFragment())
//                    .commit();
//        }
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_container_main, movieFragment, "movieFragment")
                    .add(R.id.fl_container_main, cinemaFragment, "cinemaFragment")
                    .add(R.id.fl_container_main, discoverFragment, "discoverFragment")
                    .add(R.id.fl_container_main, mineFragment, "mineFragment")
                    .commit();
            switchFragment(0);

    }


    private void setupRg() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_movie:
                        switchFragment(0);
                        break;
                    case R.id.rb_cinema:
                        switchFragment(1);
                        break;
                    case R.id.rb_discover:
                        switchFragment(2);
                        break;
                    case R.id.rb_mine:
                        switchFragment(3);
                        break;
                }
            }
        });
    }

    private void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                transaction
                        .show(movieFragment)
                        .hide(cinemaFragment)
                        .hide(discoverFragment)
                        .hide(mineFragment);
                movieFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case 1:
                transaction
                        .show(cinemaFragment)
                        .hide(movieFragment)
                        .hide(discoverFragment)
                        .hide(mineFragment);
                cinemaFragment.setUserVisibleHint(true);
                movieFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case 2:
                transaction
                        .show(discoverFragment)
                        .hide(cinemaFragment)
                        .hide(movieFragment)
                        .hide(mineFragment);
                discoverFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                movieFragment.setUserVisibleHint(false);
                mineFragment.setUserVisibleHint(false);
                break;
            case 3:
                transaction
                        .show(mineFragment)
                        .hide(cinemaFragment)
                        .hide(discoverFragment)
                        .hide(movieFragment);
                mineFragment.setUserVisibleHint(true);
                cinemaFragment.setUserVisibleHint(false);
                discoverFragment.setUserVisibleHint(false);
                movieFragment.setUserVisibleHint(false);
                break;
        }
        transaction.commit();
    }
}
