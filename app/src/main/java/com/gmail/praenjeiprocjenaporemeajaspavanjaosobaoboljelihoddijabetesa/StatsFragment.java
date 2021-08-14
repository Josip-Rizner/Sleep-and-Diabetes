package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.utils.Utils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.concurrent.TimeUnit;


public class StatsFragment extends Fragment {

    TextView yHighConnPerc, yMediumConnPerc, yLowConnPerc, yNotConnPerc, HighConnPerc, MediumConnPerc, LowConnPerc, NotConnPerc;
    int objectsSize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        yHighConnPerc = v.findViewById(R.id.yHighConnPerc);
        yMediumConnPerc = v.findViewById(R.id.yMediumConnPerc);
        yLowConnPerc = v.findViewById(R.id.yLowConnPerc);
        yNotConnPerc = v.findViewById(R.id.yNotConnPerc);
        HighConnPerc = v.findViewById(R.id.HighConnPerc);
        MediumConnPerc = v.findViewById(R.id.MediumConnPerc);
        LowConnPerc = v.findViewById(R.id.LowConnPerc);
        NotConnPerc = v.findViewById(R.id.NotConnPerc);

        retreiveConnections();

        Handler handler = new Handler();

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Dohvaćanje podataka");
        progressDialog.show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refactorData();
                showUI();
                progressDialog.dismiss();
            }
        }, 5000);



        return v;
    }

    private void showUI() {
        yHighConnPerc.setVisibility(TextView.VISIBLE);
        yMediumConnPerc.setVisibility(TextView.VISIBLE);
        yLowConnPerc.setVisibility(TextView.VISIBLE);
        yNotConnPerc.setVisibility(TextView.VISIBLE);
        HighConnPerc.setVisibility(TextView.VISIBLE);
        MediumConnPerc.setVisibility(TextView.VISIBLE);
        LowConnPerc.setVisibility(TextView.VISIBLE);
        NotConnPerc.setVisibility(TextView.VISIBLE);
    }

    private void retreiveConnections() {

        if ((ParseUser.getCurrentUser()) != null) {

            ParseUser currentUser = ParseUser.getCurrentUser();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Record");
            query.whereEqualTo("connection", "Visoka povezanost");
            query.whereEqualTo("userID", currentUser.getObjectId());
            query.findInBackground((objects, e) -> {
                if (e == null) {
                    yHighConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Record");
            query1.whereEqualTo("connection", "Srednja povezanost");
            query1.whereEqualTo("userID", currentUser.getObjectId());
            query1.findInBackground((objects, e) -> {
                if (e == null) {
                    yMediumConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Record");
            query2.whereEqualTo("connection", "Niska povezanost");
            query2.whereEqualTo("userID", currentUser.getObjectId());
            query2.findInBackground((objects, e) -> {
                if (e == null) {
                    yLowConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Record");
            query3.whereEqualTo("connection", "Nije povezano");
            query3.whereEqualTo("userID", currentUser.getObjectId());
            query3.findInBackground((objects, e) -> {
                if (e == null) {
                    yNotConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query4 = ParseQuery.getQuery("Record");
            query4.whereEqualTo("connection", "Visoka povezanost");
            query4.findInBackground((objects, e) -> {
                if (e == null) {
                    HighConnPerc.setText(String.valueOf(objects.size()));
                }
            });


            ParseQuery<ParseObject> query5 = ParseQuery.getQuery("Record");
            query5.whereEqualTo("connection", "Srednja povezanost");
            query5.findInBackground((objects, e) -> {
                if (e == null) {
                    MediumConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query6 = ParseQuery.getQuery("Record");
            query6.whereEqualTo("connection", "Niska povezanost");
            query6.findInBackground((objects, e) -> {
                if (e == null) {
                    LowConnPerc.setText(String.valueOf(objects.size()));
                }
            });

            ParseQuery<ParseObject> query7 = ParseQuery.getQuery("Record");
            query7.whereEqualTo("connection", "Nije povezano");
            query7.findInBackground((objects, e) -> {
                if (e == null) {
                    NotConnPerc.setText(String.valueOf(objects.size()));
                }
            });
        }
    }

    private void refactorData() {
        double highConn, medConn, lowConn, notConn, sum;

        highConn = Double.parseDouble(yHighConnPerc.getText().toString());
        medConn = Double.parseDouble(yMediumConnPerc.getText().toString());
        lowConn = Double.parseDouble(yLowConnPerc.getText().toString());
        notConn = Double.parseDouble(yNotConnPerc.getText().toString());

        sum = highConn + medConn + lowConn + notConn;

        yHighConnPerc.setText(String.format("%.2f",highConn/sum*100) + "%");
        yMediumConnPerc.setText(String.format("%.2f",medConn/sum*100) + "%");
        yLowConnPerc.setText(String.format("%.2f",lowConn/sum*100) + "%");
        yNotConnPerc.setText(String.format("%.2f",notConn/sum*100) + "%");

        sum = highConn = medConn = lowConn = notConn = 0;

        highConn = Double.parseDouble(HighConnPerc.getText().toString());
        medConn = Double.parseDouble(MediumConnPerc.getText().toString());
        lowConn = Double.parseDouble(LowConnPerc.getText().toString());
        notConn = Double.parseDouble(NotConnPerc.getText().toString());

        sum = highConn + medConn + lowConn + notConn;

        HighConnPerc.setText(String.format("%.2f",highConn/sum*100) + "%");
        MediumConnPerc.setText(String.format("%.2f",medConn/sum*100) + "%");
        LowConnPerc.setText(String.format("%.2f",lowConn/sum*100) + "%");
        NotConnPerc.setText(String.format("%.2f",notConn/sum*100) + "%");

    }
}
