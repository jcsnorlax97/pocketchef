package net.team5.pocketchef.ui;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import net.team5.pocketchef.R;

public class Messages {
    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}

