package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddFragment extends Fragment {

    Button btnAdd;
    EditText etMorningGlucoze, etNightGlucoze, etEveningGlucoze, etNumberOfWakingUp, etSleepingTime;
    RadioGroup rgMorningFeeling;
    RadioButton rbRested, rbTired, rbHardToSay;


    String diseaseConnection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_add, container, false);


        btnAdd = v.findViewById(R.id.bAdd);
        etMorningGlucoze = v.findViewById(R.id.etMorningGlucoze);
        etEveningGlucoze = v.findViewById(R.id.etEveningGlucoze);
        etNightGlucoze = v.findViewById(R.id.etNightGlucoze);
        etNumberOfWakingUp = v.findViewById(R.id.etNumberOfWakingUp);
        etSleepingTime = v.findViewById(R.id.etSleepingTime);
        rgMorningFeeling = v.findViewById(R.id.rgMorningFeeling);
        rbRested = v.findViewById(R.id.rbRested);
        rbTired = v.findViewById(R.id.rbTired);
        rbHardToSay = v.findViewById(R.id.rbHardToSay);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });

        return v;
    }


    private void checkInputs(){


        if(TextUtils.isEmpty(etEveningGlucoze.getText())){
            etEveningGlucoze.setError("Ovo polje je obavezno!");
        }
        else if(TextUtils.isEmpty(etMorningGlucoze.getText())){
            etMorningGlucoze.setError("Ovo polje je obavezno!");
        }
        else if(TextUtils.isEmpty(etNightGlucoze.getText())){
            etNightGlucoze.setError("Ovo polje je obavezno!");
        }
        else if(TextUtils.isEmpty(etSleepingTime.getText())){
            etSleepingTime.setError("Ovo polje je obavezno!");
        }
        else if(TextUtils.isEmpty(etNumberOfWakingUp.getText())){
            etNumberOfWakingUp.setError("Ovo polje je obavezno!");
        }
        else if(rgMorningFeeling.getCheckedRadioButtonId() == -1){
            rbRested.setError("Ovo polje je obavezno!");
            rbTired.setError("Ovo polje je obavezno!");
            rbHardToSay.setError("Ovo polje je obavezno!");
        }
        else {
            addRecord();
            clearInputs();
        }

    }



    private void addRecord(){

        DateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy. HH:mm:ss");

        ParseUser currentUser = ParseUser.getCurrentUser();

        diseaseConnection = checkDiseaseConnection();

        ParseObject record = new ParseObject("Record");
        record.put("userID", currentUser.getObjectId());
        record.put("creationTime", dateFormat.format(Calendar.getInstance().getTime()));
        record.put("connection", diseaseConnection);
        record.put("eveningGlucoze", etEveningGlucoze.getText().toString().trim());
        record.put("nightGlucoze", etNightGlucoze.getText().toString().trim());
        record.put("morningGlucoze", etMorningGlucoze.getText().toString().trim());
        record.put("numberOfWakingUp", etNumberOfWakingUp.getText().toString().trim());
        record.put("sleepingTime", etSleepingTime.getText().toString().trim());
        record.put("sleepRating", rateSleep());
        record.put("glukozeConcentrationRating", rateGlucozeConcentration());


        if(rbTired.isChecked()){
            record.put("morningFeeling", "Umorno");
        }
        else  if(rbRested.isChecked()){
            record.put("morningFeeling", "Odmorno");
        }
        else{
            record.put("morningFeeling", "Teško je reći");
        }


        record.saveInBackground(e -> {
            if (e == null){
                Toast.makeText(getContext(), "Uneseno u bazu", Toast.LENGTH_LONG).show();
                openDialog();
            }
            else{
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    private String checkDiseaseConnection(){

        double sleepRating, glucozeConcentrationRating;

        sleepRating = rateSleep();
        glucozeConcentrationRating = rateGlucozeConcentration();

        return evaluateResults(sleepRating, glucozeConcentrationRating);
    }



    private String evaluateResults(double sleepRating, double glucozeConcentrationRating){

        String diseaseConnection = "";

        if(glucozeConcentrationRating == 0){
            diseaseConnection = "Nije povezano";
        }

        else if(glucozeConcentrationRating == 1 || glucozeConcentrationRating == 2){
            if(sleepRating <= 60){
                diseaseConnection = "Niska povezanost";
            }
            else{
                diseaseConnection = "Nije povezano";
            }

        }

        else if(glucozeConcentrationRating == 3 || glucozeConcentrationRating == 4){
            if(sleepRating <= 60){
                diseaseConnection = "Srednja povezanost";
            }
            else if(sleepRating > 60 && sleepRating <= 90){
                diseaseConnection = "Niska povezanost";
            }
            else{
                diseaseConnection = "Nije povezano";
            }
        }

        else if(glucozeConcentrationRating == 5 || glucozeConcentrationRating == 6){
            if(sleepRating <= 50){
                diseaseConnection = "Visoka povezanost";
            }
            else if(sleepRating > 50 && sleepRating <= 70){
                diseaseConnection = "Srednja povezanost";
            }
            else if(sleepRating > 70 && sleepRating <= 85){
                diseaseConnection = "Niska povezanost";
            }
            else{
                diseaseConnection = "Nije povezano";
            }
        }

        return diseaseConnection;
    }


    private double rateSleep(){
        double sleepRating = 0;

        double sleepTime = Double.parseDouble(etSleepingTime.getText().toString());
        double numberOfWakingUp = Double.parseDouble(etNumberOfWakingUp.getText().toString());
        double REM_cycles = sleepTime / 1.5;
        double achieved_REM_cycles = REM_cycles - numberOfWakingUp;

        sleepRating = sleepTime * 11.5;

        if(sleepRating > 100){sleepRating =100;}


        if(achieved_REM_cycles < 1){
            sleepRating *= 0.20;
        }
        else if(achieved_REM_cycles >= 1 && achieved_REM_cycles <= 3){
            sleepRating *= 0.5;
        }
        else if(achieved_REM_cycles > 3 && achieved_REM_cycles <= 5){
            sleepRating *= 0.9;
        }
        else if(achieved_REM_cycles > 5 && numberOfWakingUp != 0){
            sleepRating *= 0.95;
        }
        else if(achieved_REM_cycles > 5 && numberOfWakingUp == 0){
            sleepRating = 100;
        }

        if(sleepRating >= 80){
            if(rbTired.isChecked()){
                sleepRating -= 30;
            }
            else if(rbHardToSay.isChecked()){
                sleepRating -= 15;
            }
        }
        else if(sleepRating >= 45 && sleepRating < 80){
            if(rbTired.isChecked()){
                sleepRating -= 20;
            }
            else if(rbHardToSay.isChecked()){
                sleepRating -= 10;
            }
            else if(rbRested.isChecked()){
                sleepRating += 10;
            }
        }
        else if(sleepRating < 45){
            if(rbTired.isChecked()){
                sleepRating -= 15;
            }
            else if(rbHardToSay.isChecked()){
                sleepRating -= 5;
            }
            else if(rbRested.isChecked()){
                sleepRating += 20;
            }
        }

        if(sleepRating <= 0 ){sleepRating = 0;}

        return sleepRating;
    }

    private double rateGlucozeConcentration(){

        double eveningGlucoze = Double.parseDouble(etEveningGlucoze.getText().toString());
        double morningGlucoze = Double.parseDouble(etMorningGlucoze.getText().toString());
        double nightGlucoze = Double.parseDouble(etNightGlucoze.getText().toString());
        double glucozeConcentrationRating = 0;

        /*Glukoze concentration in blood goal value is between 3.9mmol/L and 10mmol/L */

        if(eveningGlucoze < 3.9){
            glucozeConcentrationRating += 1;
        }
        if(nightGlucoze < 3.9){
            glucozeConcentrationRating += 1;
        }
        if(morningGlucoze < 3.9){
            glucozeConcentrationRating += 1;
        }
        if(eveningGlucoze >= 10 && eveningGlucoze < 13.9){
            glucozeConcentrationRating += 1;
        }
        if(nightGlucoze >= 10 && nightGlucoze < 13.9){
            glucozeConcentrationRating += 1;
        }
        if(morningGlucoze >= 10 && morningGlucoze < 13.9){
            glucozeConcentrationRating += 1;
        }
        if(eveningGlucoze >= 13.9){
            glucozeConcentrationRating += 2;
        }
        if(nightGlucoze >= 13.9){
            glucozeConcentrationRating += 2;
        }
        if(morningGlucoze >= 13.9){
            glucozeConcentrationRating += 2;
        }

        return glucozeConcentrationRating;
    }


    private void clearInputs(){

        etEveningGlucoze.setText("");
        etMorningGlucoze.setText("");
        etNightGlucoze.setText("");
        etSleepingTime.setText("");
        etNumberOfWakingUp.setText("");
        rgMorningFeeling.clearCheck();

    }

    public void openDialog(){
        ConnectionDialog connectionDialog = new ConnectionDialog(diseaseConnection);
        connectionDialog.show(getFragmentManager(), "Connecion dialog");
    }
}



