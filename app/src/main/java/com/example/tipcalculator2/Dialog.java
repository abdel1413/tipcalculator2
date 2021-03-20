package com.example.tipcalculator2;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Intructions")
                .setMessage("1) No : the exact bill, tip, total will be used in calculations \n \n" +
                        "2) Tip : the tip will be rounded up before added to the bill to calculate the exact total \n \n" +
                        "3) Total : the bill and tip remain exact, but the total will be rounded up ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
