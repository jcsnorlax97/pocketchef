package net.team5.pocketchef.ui.user_profile;
import android.graphics.Color;
import android.text.Html;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDialogFragment;

public class FaqDialog extends AppCompatDialogFragment {
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), net.team5.pocketchef.R.style.MyDialogTheme);
        builder.setTitle("Frequently Asked Questions")
                .setMessage("1. Where can I add my recipe?\n"+"You can add a recipe by going to the navigation bar at the bottom and pressing on \"Add Recipe\"\n\n"+
                        "2. Where can I add my ingredients to the recipes?\n"+"You can add your ingredients when you create a recipe under \"Add Recipe\"\n\n"+
                        "3. How can I change my search?\n"+"You can change you search options by pressing on the small boxes indicating different options")
                .setNegativeButton(Html.fromHtml("Ok"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        Button nButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nButton.setTextColor(Color.WHITE);
        return alert;
    }
}
