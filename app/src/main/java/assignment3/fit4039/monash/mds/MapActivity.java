package assignment3.fit4039.monash.mds;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final LatLng LOCATION_CAULFIELD
            = new LatLng(-37.8770, 145.0443);


    private GoogleMap m_cGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFrag = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map_fragment);
        // Set up an asyncronous callback to let us know when the map has loaded
        mapFrag.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        m_cGoogleMap = googleMap;
        // Move the focus of the map to be on Caulfield Campus. 15 is for zoom
        m_cGoogleMap.addMarker(new MarkerOptions()
                .position(LOCATION_CAULFIELD).title("Monash Caulfield"));
        m_cGoogleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(LOCATION_CAULFIELD, 15));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        m_cGoogleMap.setMyLocationEnabled(true);
    }



}
