package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPass, etConfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfPass = findViewById(R.id.etConfPass);
    }


    public void signup(View view) {
        if(TextUtils.isEmpty(etName.getText())){
            etName.setError("Korisničko ime je obavezno!");
        }
        else if(TextUtils.isEmpty(etEmail.getText())){
            etEmail.setError("Email je obavezan!");
        }
        else if(TextUtils.isEmpty(etPass.getText())){
            etPass.setError("Lozinka je obavezna!");
        }
        else if(TextUtils.isEmpty(etConfPass.getText())){
            etConfPass.setError("Ponovite lozinku!");
        }
        else if(!etPass.getText().toString().equals(etConfPass.getText().toString())){
            Toast.makeText(SignupActivity.this, "Lozinke nisu jednake!", Toast.LENGTH_SHORT).show();
        }
        else{

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registracija");
            progressDialog.show();

            ParseUser user = new ParseUser();
            // Set the user's username and password, which can be obtained by a forms
            user.setUsername(etName.getText().toString().trim());
            user.setEmail(etEmail.getText().toString().trim());
            user.setPassword(etPass.getText().toString().trim());
            user.put("name", etName.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    progressDialog.dismiss();

                    if (e == null) {

                        Toast.makeText(SignupActivity.this, "Dobrodošli!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public  void login(View view){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}