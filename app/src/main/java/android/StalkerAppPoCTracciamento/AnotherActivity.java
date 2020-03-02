package android.StalkerAppPoCTracciamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.NetworkOnMainThreadException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.PolyUtil;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import android.os.AsyncTask;
import java.lang.Thread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.firebase.auth.FirebaseAuth;

/*
public class AnotherActivity extends AppCompatActivity {
    ImageView imageView;
    TextView Organizzazione;
    FirebaseAuth fAuth;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_act);
        //if(fAuth.getCurrentUser() != null){
            //startActivity(new Intent(getApplicationContext(),ListaOrganizzazioni.class));
           // finish();
        //}

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
}
*/


//////////////////////////////////////VERSIONE CON LE MODIFICHE DI RICCARDO/////////////////////////////////////
public class AnotherActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener listener;
    private RequestQueue mQueue;
    TextView Organizzazione;
    TextView pos;
    Button scarica;
    Button aggiorna;
    FirebaseAuth fAuth;
    int position;
    final  ArrayList<LatLng> poligono = new ArrayList<>();
    final  LatLngBounds.Builder builder = new LatLngBounds.Builder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_act);
        scarica=(Button)findViewById(R.id.coordinate);
        aggiorna=(Button)findViewById(R.id.aggiorna);
        //if(fAuth.getCurrentUser() != null){
        //startActivity(new Intent(getApplicationContext(),ListaOrganizzazioni.class));
        // finish();
        //}
        pos=(TextView)findViewById(R.id.text_view_result);
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
        mQueue = Volley.newRequestQueue(this);
        scarica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parse();
                CostruisciPol();
                Posizione();
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
    private void Parse(){
        String url = "https://api.myjson.com/bins/17t4ai";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Organizzazioni");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject org = jsonArray.getJSONObject(i);
                                String organizzazione1=org.getString("lat");
                                String organizzazione2=org.getString("long");
                                Double o1=Double.parseDouble(organizzazione1);
                                Double o2=Double.parseDouble(organizzazione2);
                                setCoordinate(o1,o2);
                                // pos.append(organizzazione1 +" "+ organizzazione2+ "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
    private void setCoordinate(Double lat, Double lon){
        poligono.add(new LatLng(lat,lon));
    }
    private void Posizione(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                pos.setText(" ");
                pos.append("\n " + location.getLongitude() + " " + location.getLatitude());
                //locationManager.removeUpdates(this); BLOCCO Updates tramite pulsante
                LatLng test = new LatLng(location.getLatitude(),location.getLongitude());
                boolean isInsideBoundary = builder.build().contains(test); // true as the test point is inside the boundary
                boolean isInside = PolyUtil.containsLocation(test, poligono, true); // false as the test point is outside the polygon
                if (isInsideBoundary==true && isInside==true)
                    pos.append("\n"+"Sei dentro");
                else
                    pos.append("\n"+"sei fuori");
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
        aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                try {
                    locationManager.requestLocationUpdates("gps", 15000, 0, listener);
                }
                catch (SecurityException e) {
                    e.getMessage();
                }
            }
        });
    }
    private void CostruisciPol(){
        for (LatLng point : poligono) {
            builder.include(point);
        }
    }
}