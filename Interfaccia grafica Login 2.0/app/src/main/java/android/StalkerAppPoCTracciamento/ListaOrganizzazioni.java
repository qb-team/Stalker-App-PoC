package android.StalkerAppPoCTracciamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.NetworkOnMainThreadException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.android.PolyUtil;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import android.os.AsyncTask;
import java.lang.Thread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListaOrganizzazioni extends AppCompatActivity {
    ListView listaOrg;
    FirebaseAuth fAuth;

    String Organizzazione[] = {"Torre Archimede", "Azienda"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organizzazioni);
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
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
