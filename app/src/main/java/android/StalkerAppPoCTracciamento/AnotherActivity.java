package android.StalkerAppPoCTracciamento;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.KeyEvent;
import android.view.View;

import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.Button;

import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;

import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.PolyUtil;


import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AnotherActivity extends AppCompatActivity {
    TextView Organizzazione;
    FirebaseAuth fAuth;
    int position;
    private String risposta;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener listener;
    private Button b;
    private RequestQueue mQueue;
    final ArrayList<LatLng> poligono = new ArrayList<>();
    final LatLngBounds.Builder builder = new LatLngBounds.Builder();





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_act);
        fAuth = FirebaseAuth.getInstance();
        t = (TextView) findViewById(R.id.text_view_result);
        b = (Button) findViewById(R.id.coordinate);
        Parse();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                t.setText(" ");
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                //locationManager.removeUpdates(this); BLOCCO Updates tramite pulsante
                LatLng test = new LatLng(location.getLatitude(), location.getLongitude());
                boolean isInsideBoundary = builder.build().contains(test); // true as the test point is inside the boundary
                boolean isInside = PolyUtil.containsLocation(test, poligono, true); // false as the test point is outside the polygon
                if (isInsideBoundary == true && isInside == true)
                    t.append("\n" + "Sei dentro");
                else
                    t.append("\n" + "sei fuori");
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        Organizzazione = findViewById(R.id.titleText);
        if (position == 0) {
            Intent intent = getIntent();
            Bundle bundle = this.getIntent().getExtras();
            //int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            //imageView.setImageResource(pic);
            Organizzazione.setText(aTitle);
            actionBar.setTitle(aTitle);
        }
        if (position == 1) {
            Intent intent = getIntent();
            Bundle bundle = this.getIntent().getExtras();
            //int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            //imageView.setImageResource(pic);
            Organizzazione.setText(aTitle);
            actionBar.setTitle(aTitle);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                try {
                    locationManager.requestLocationUpdates("gps", 15000, 0, listener);
                } catch (SecurityException e) {
                    e.getMessage();
                }
            }
        });
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void Parse() {
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());


        mQueue = new RequestQueue(cache, network);

// Start the queue
        mQueue.start();
        String url = "https://api.myjson.com/bins/17t4ai";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        risposta=response;

                        try {


                            JSONObject jObject = new JSONObject(risposta);
                            JSONArray jsonArray = jObject.getJSONArray("Organizzazioni");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject org = jsonArray.getJSONObject(i);
                                String organizzazione1 = org.getString("lat");
                                String organizzazione2 = org.getString("long");
                                double o1 = Double.parseDouble(organizzazione1);
                                double o2 = Double.parseDouble(organizzazione2);
                                setCoordinate(o1, o2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (LatLng point : poligono) {
                            builder.include(point);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        mQueue.add(stringRequest);


    }

    public void setCoordinate(double lat,double lon){
        poligono.add(new LatLng(lat, lon));
        System.out.println(lat+" "+lon);
    }


}