package net.team5.pocketchef.ui.user_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.team5.pocketchef.MainActivity;
import net.team5.pocketchef.R;

public class UserProfileFragment extends Fragment {


    Button btn_about, btn_viewRecipe, btn_bug, btn_faq;

    private UserProfileViewModel userProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                ViewModelProviders.of(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_profile, container, false);

        //assigning buttons for each
        btn_about = (Button) root.findViewById(R.id.buttonInfo);
        btn_bug = (Button) root.findViewById(R.id.buttonReportBug);
        btn_faq = (Button) root.findViewById(R.id.buttonFAQ);
        btn_viewRecipe = (Button) root.findViewById(R.id.buttonViewRecipes);

        //setting listener to display build information about the app and the developers
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutDialog();
            }
        });
        //setting listener to display information to report bug
        btn_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReportBugDialog();
            }
        });
        //setting listener to view recipes that were made by user. Implemented by Iteration3.
        btn_viewRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Coming Soon \n Soon you will be able to view your recipes that you created here", Toast.LENGTH_SHORT).show();

            }
        });
        //setting listener to display frequently asked questions (FAQ) coming soon
        btn_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFaqDialog();
            }
        });

        return root;
    }

    public void openReportBugDialog() {
        ReportBugDialog bugDialog = new ReportBugDialog();
        bugDialog.show(getFragmentManager(), "bugDialog");
    }

    public void openAboutDialog() {
        AboutDialog aboutDialog = new AboutDialog();
        aboutDialog.show(getFragmentManager(), "aboutDialog");
    }

    public void openFaqDialog() {
        FaqDialog faqDialog = new FaqDialog();
        faqDialog.show(getFragmentManager(), "faqDialog");
    }
}