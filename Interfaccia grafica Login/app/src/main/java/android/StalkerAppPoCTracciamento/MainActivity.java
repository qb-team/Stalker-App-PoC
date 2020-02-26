package android.StalkerAppPoCTracciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonL, buttonR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonL = (Button) findViewById(R.id.buttonLogin);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLogin();
            }
        });
        buttonR = (Button) findViewById(R.id.buttonRegistrati);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegistrati();
            }
        });
    }

    public void openActivityLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openActivityRegistrati() {
        Intent intent = new Intent(this, Registrati.class);
        startActivity(intent);
    }
}

