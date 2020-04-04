package net.team5.pocketchef.ui.user_profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class FaqDialog extends AppCompatDialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Frequently Asked Questions")
                .setMessage("1. Where can I add my recipe?\n"+"You can find your recipe at the add recipe section or go to the home screen and press on \"Search Recipe\"\n\n"+
                        "2. Where can I add my ingredients to the recipes?\n"+"You can add your ingredients when you create a recipe under \"Add Recipe\"")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
