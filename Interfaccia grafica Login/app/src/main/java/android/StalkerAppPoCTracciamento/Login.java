package android.StalkerAppPoCTracciamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button buttonLogin, buttonBack;
    EditText emailText, passwordText;

    TextView tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button)findViewById(R.id.buttonAccesso);
        emailText = (EditText)findViewById(R.id.editText);
        passwordText = (EditText)findViewById(R.id.editText2);

        buttonBack = (Button)findViewById(R.id.buttonCancel);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailText.getText().toString().equals("admin") &&
                        passwordText.getText().toString().equals("admin")) {//modificare con il server
                    Toast.makeText(getApplicationContext(),
                            "Credenziali corrette",Toast.LENGTH_SHORT).show();

                    ListaOrganizzazioni organizzazioni = new ListaOrganizzazioni();
                    openActivityListaOrganizzazioni();
                }else{
                    Toast.makeText(getApplicationContext(), "Credenziali errate",Toast.LENGTH_SHORT).show();
                }
            }
        });



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void openActivityListaOrganizzazioni() {
        Intent intent = new Intent(this, ListaOrganizzazioni.class);
        startActivity(intent);
    }
}
