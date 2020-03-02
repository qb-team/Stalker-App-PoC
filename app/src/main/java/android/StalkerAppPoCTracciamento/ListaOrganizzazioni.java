package android.StalkerAppPoCTracciamento;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListaOrganizzazioni extends AppCompatActivity {
    ListView listaOrg;
    FirebaseAuth fAuth;
    private LocationManager locationManager;
    private LocationListener listener;
    final ArrayList<LatLng> poligono = new ArrayList<>();
    final LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private RequestQueue mQueue;
    private String risposta;

    String Organizzazione[] = {"Torre Archimede", "Azienda"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organizzazioni);
        fAuth = FirebaseAuth.getInstance();

       /* mQueue = Volley.newRequestQueue(this);

// Start the queue

        String url = "https://api.myjson.com/bins/17t4ai";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONObject jObject = new JSONObject(response);
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


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        mQueue.add(stringRequest);
        for (LatLng point : poligono) {
            builder.include(point);
        }*/

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                LatLng test = new LatLng(location.getLatitude(), location.getLongitude());
               boolean isInsideBoundary = builder.build().contains(test); // true as the test point is inside the boundary
                boolean isInside = PolyUtil.containsLocation(test, poligono, true); // false as the test point is outside the polygon
                if ( isInsideBoundary==true && isInside == true)
                {Context context = getApplicationContext();
                    CharSequence text = "Sei Dentro"+ Organizzazione[0];
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();}

                else
                {Context context = getApplicationContext();
                    CharSequence text = "Sei Fuori"+ Organizzazione[0];
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();}
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

       // configure_button();




        listaOrg =  findViewById(R.id.ListaOrg);
        MyAdapter adapter = new MyAdapter(this, Organizzazione);

        listaOrg.setAdapter(adapter);
        listaOrg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    intent.putExtra("title", Organizzazione[0]);
                    intent.putExtra("position", ""+0);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    intent.putExtra("title", Organizzazione[1]);
                    intent.putExtra("position", ""+1);
                    startActivity(intent);
                }
            }
        });
    }
    /*@Override
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


                //noinspection MissingPermission
                try {



                    locationManager.requestLocationUpdates("gps", 0, 0, listener);
                } catch (SecurityException e) {
                    e.getMessage();
                }

    }*/



    public void setCoordinate(double lat,double lon){
        poligono.add(new LatLng(lat, lon));
        System.out.println(5);

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String Organizzazione[];
        MyAdapter (Context c, String title[]) {
            super(c, R.layout.row, R.id.textView2, title);//R.id.textView1
            this.context = c;
            this.Organizzazione = title;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView2);
            myTitle.setText(Organizzazione[position]);
            return row;
        }
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    @Override
    public void onBackPressed() {
       // AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = new AlertDialog.Builder(ListaOrganizzazioni.this).
        setTitle("Attenzione").setMessage("Sei sicuro di uscire ?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).
        setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        }).show();
    }

}
