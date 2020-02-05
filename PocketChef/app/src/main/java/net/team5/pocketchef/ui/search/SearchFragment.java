/*
package net.team5.pocketchef.ui.search;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.team5.pocketchef.MainActivity;
import net.team5.pocketchef.R;
import net.team5.pocketchef.ui.dashboard.DashboardViewModel;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private SearchView schView;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*NOTE:
            This method I am 99% sure is not helpful and can be deleted
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

         */

/*
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        schView = (SearchView) view.findViewById(R.id.searchRecipesView);

        schView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Seaching recipes", Toast.LENGTH_SHORT).show();

                //navigate to fragment
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        return view;
    }
}
 */
