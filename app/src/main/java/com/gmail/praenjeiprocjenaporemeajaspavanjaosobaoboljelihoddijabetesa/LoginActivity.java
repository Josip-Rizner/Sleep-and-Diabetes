package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {
        if(TextUtils.isEmpty(etEmail.getText())){
            etEmail.setError("Email je obavezan!");
        }
        else if(TextUtils.isEmpty(etPass.getText())){
            etPass.setError("Lozinka je obavezna!");
        }
        else{

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Prijava");
                progressDialog.show();

                ParseUser.logInInBackground(etEmail.getText().toString(), etPass.getText().toString(), (parseUser, e) -> {

                    progressDialog.dismiss();

                    if (parseUser != null) {

                        Toast.makeText(LoginActivity.this, "Dobrodošli nazad!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        ParseUser.logOut();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        }
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void changePassword(View view){
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }
}