package android.StalkerAppPoCTracciamento;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog mProgressDialog;
    Button  buttonBack;
    SignInButton buttonLogin;
    RelativeLayout mRelative;
    EditText emailText, passwordText;
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (SignInButton)findViewById(R.id.buttonAccesso);
        buttonLogin.setOnClickListener(this);
        emailText = (EditText)findViewById(R.id.editText);
        passwordText = (EditText)findViewById(R.id.editText2);
        mRelative = (RelativeLayout) findViewById(R.id.relativeLayoutUserInfo);
        buttonBack = (Button)findViewById(R.id.buttonCancel);


        mFirebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("NKeHzKc6UIXddIJtDSd1AGoefaf2")
        // .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

      /* buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.buttonAccesso) {
                    signIn();
                } //else if (i == R.id.bt_log_out) {
                // signOut();
                // }
            }
            /*public void onClick(View v) {
                if(emailText.getText().toString().equals("a") &&
                        passwordText.getText().toString().equals("a")) {//modificare con il server
                    Toast.makeText(getApplicationContext(),
                            "Credenziali corrette",Toast.LENGTH_SHORT).show();

                    ListaOrganizzazioni organizzazioni = new ListaOrganizzazioni();
                    openActivityListaOrganizzazioni();
                }else{
                    Toast.makeText(getApplicationContext(), "Credenziali errate",Toast.LENGTH_SHORT).show();
                }
            }*/
        //})




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



    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
           mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            updateUI(null);
        }
    }
}
private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
}

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        showProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressDialog();
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            buttonLogin.setVisibility(View.GONE);
            mRelative.setVisibility(View.VISIBLE);
            emailText.setText(user.getDisplayName());
            //String imageUrl = user.getPhotoUrl().toString();
            //imageUrl = imageUrl.replace("/s96-c/","/s200-c/");
            String userId = "";
            for(UserInfo profile : user.getProviderData()){
                if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                    userId = profile.getUid();
                }
            }

        } else {
            buttonLogin.setVisibility(View.VISIBLE);
            mRelative.setVisibility(View.GONE);
        }
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    private void signOut() {
        mFirebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        updateUI(null);
                    }
                });
    }
public void onClick(View v) {
    int i = v.getId();
    if (i == R.id.buttonAccesso) {
        signIn();
    } //else if (i == R.id.bt_log_out) {
    // signOut();
}
}

