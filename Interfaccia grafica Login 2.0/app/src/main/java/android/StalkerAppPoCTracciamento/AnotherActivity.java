package android.StalkerAppPoCTracciamento;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}