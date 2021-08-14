package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class HomeFragment extends Fragment {

    //TextView tvEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //ParseUser currentUser = ParseUser.getCurrentUser();

        //tvEmail = v.findViewById(R.id.tvEmail);


        /*if(currentUser != null)
        {
            tvEmail.setText(currentUser.getEmail());

        }*/

        return v;
    }


}
