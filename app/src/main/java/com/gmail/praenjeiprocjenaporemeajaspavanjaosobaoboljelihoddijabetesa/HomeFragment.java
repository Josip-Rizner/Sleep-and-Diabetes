package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

    TextView tvReadMoreDiabetes, tvReadMoreSleep;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tvReadMoreDiabetes = v.findViewById(R.id.tvReadMoreDiabetes);
        tvReadMoreSleep = v.findViewById(R.id.tvReadMoreSleep);

        tvReadMoreDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://hr.wikipedia.org/wiki/%C5%A0e%C4%87erna_bolest");
            }
        });

        tvReadMoreSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.plivazdravlje.hr/aktualno/clanak/2284/Poremecaji-spavanja.html");
            }
        });

        return v;
    }

    private void gotoUrl(String string) {
        Uri uri = Uri.parse(string);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


}
