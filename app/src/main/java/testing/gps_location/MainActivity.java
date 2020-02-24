package testing.gps_location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.PolyUtil;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button b;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener listener;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.textView);
        b = (Button) findViewById(R.id.button);



        // Construct a List<LatLng> representing a Polygon
       final  ArrayList<LatLng> poligono = new ArrayList<>();

        poligono.add(new LatLng(45.411222,11.887317));
        poligono.add(new LatLng(45.411555,11.887474));
        poligono.add(new LatLng(45.411440,11.887946));
        poligono.add(new LatLng(45.411109,11.887786));


        // Find the LatLngBounds of the Polygon
       final  LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng point : poligono) {
            builder.include(point);
        }




        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                t.setText(" ");
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                locationManager.removeUpdates(this);

                LatLng test = new LatLng(location.getLatitude(),location.getLongitude());
                boolean isInsideBoundary = builder.build().contains(test); // true as the test point is inside the boundary
                boolean isInside = PolyUtil.containsLocation(test, poligono, true); // false as the test point is outside the polygon
                if (isInsideBoundary==true && isInside==true)
                    t.append("\n"+"Sei dentro");
                else t.append("\n"+"sei fuori");


            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                    configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                try {
                    locationManager.requestLocationUpdates("gps", 0, 0, listener);
                }
                catch (SecurityException e) {
                    e.getMessage();

                }
            }
        });
    }
}





