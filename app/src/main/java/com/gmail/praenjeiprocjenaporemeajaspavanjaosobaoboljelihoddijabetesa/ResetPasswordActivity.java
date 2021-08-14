package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etSendEmail = findViewById(R.id.etSendEmail);
    }


    public void sendEmail(View view){

        if(TextUtils.isEmpty(etSendEmail.getText())){
            etSendEmail.setError("Email je obavezan!");
        }
        else {
            ParseUser.requestPasswordResetInBackground(etSendEmail.getText().toString(), new RequestPasswordResetCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){
                        Toast.makeText(ResetPasswordActivity.this, "Email s koracima za resetiranje lozinke je poslan.", Toast.LENGTH_LONG).show();
                        etSendEmail.setText("");
                    }
                    else {
                        Toast.makeText(ResetPasswordActivity.this, "Nešto je pošlo po krivu..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }



    public void backToLogIn(View view){
        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}