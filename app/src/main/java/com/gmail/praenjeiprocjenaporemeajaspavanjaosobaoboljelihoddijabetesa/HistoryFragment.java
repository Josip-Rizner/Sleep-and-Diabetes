package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View listItemsView;

    ArrayList<String> retreivedRecords;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        retreiveRecords();

        listItemsView = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = listItemsView.findViewById(R.id.rvHistory);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return listItemsView;
    }


    public void retreiveRecords() {

        if ((ParseUser.getCurrentUser()) != null) {

            ParseUser currentUser = ParseUser.getCurrentUser();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Record");
            query.whereEqualTo("userID", currentUser.getObjectId());
            query.orderByDescending("createdAt");

            query.findInBackground((objects, e) -> {


                if (e == null && objects.size() > 0) {

                    retreivedRecords = new ArrayList<>();

                    for(ParseObject record: objects){

                        retreivedRecords.add(record.get("creationTime").toString());
                        retreivedRecords.add(record.get("connection").toString());
                        retreivedRecords.add(record.get("eveningGlucoze").toString());
                        retreivedRecords.add(record.get("morningGlucoze").toString());
                        retreivedRecords.add(record.get("nightGlucoze").toString());
                        retreivedRecords.add(record.get("sleepingTime").toString());
                        retreivedRecords.add(record.get("numberOfWakingUp").toString());
                        retreivedRecords.add(record.get("morningFeeling").toString());
                    }



                    adapter = new HistoryAdapter(retreivedRecords, objects.size());
                    recyclerView.setAdapter(adapter);


                }

            });

        }
    }



}
