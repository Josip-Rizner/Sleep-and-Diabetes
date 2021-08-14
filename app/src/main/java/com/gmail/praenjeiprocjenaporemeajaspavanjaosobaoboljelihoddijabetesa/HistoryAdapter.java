package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AppViewHolder> {

    int listlength;
    ArrayList<String> arrayList;

    private LayoutInflater layoutInflater;


    HistoryAdapter(ArrayList<String> list, int lenght){

        arrayList = list;
        listlength = lenght;
    }


    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

            holder.tvDate.setText("Uneseno: "  + arrayList.get(position*8));
            holder.tvConnection.setText(arrayList.get(position*8+1).toUpperCase());
            holder.tvEveningGlukoze.setText(arrayList.get(position*8+2) + " mmol/L");
            holder.tvMorningGlukoze.setText(arrayList.get(position*8+3) + " mmol/L");
            holder.tvNightGlukoze.setText(arrayList.get(position*8+4) + " mmol/L");
            holder.tvSleepingTime.setText(arrayList.get(position*8+5) + " h");
            holder.tvNumberOfWakingUp.setText(arrayList.get(position*8+6) + " puta");
            holder.tvMorningFeeling.setText(arrayList.get(position*8+7));
    }

    @Override
    public int getItemCount() {
        return listlength;
    }



    public class AppViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvConnection, tvEveningGlukoze, tvNightGlukoze, tvMorningGlukoze, tvSleepingTime, tvNumberOfWakingUp, tvMorningFeeling;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvConnection = itemView.findViewById(R.id.tvConnection);
            tvEveningGlukoze = itemView.findViewById(R.id.tvEveningGlukoze);
            tvNightGlukoze = itemView.findViewById(R.id.tvNightGlukoze);
            tvMorningGlukoze = itemView.findViewById(R.id.tvMorningGlukoze);
            tvSleepingTime = itemView.findViewById(R.id.tvSleepingTime);
            tvNumberOfWakingUp = itemView.findViewById(R.id.tvNumberOfWakingUp);
            tvMorningFeeling = itemView.findViewById(R.id.tvMorningFeeling);
        }
    }
}
