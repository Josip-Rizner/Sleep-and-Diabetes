package com.gmail.praenjeiprocjenaporemeajaspavanjaosobaoboljelihoddijabetesa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConnectionDialog extends AppCompatDialogFragment {

    String diseaseConnection;

    ConnectionDialog(String connection){
        diseaseConnection = connection;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Rezultat:")
                .setMessage("Procjena povezanosti kvalitete sna i dijabetesa: \n\n" + diseaseConnection.toUpperCase() + "\n\n\nSve unose možete pogledati u povijesti.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }


}
