package net.team5.pocketchef.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import net.team5.pocketchef.R;
import net.team5.pocketchef.ui.search_recipes.DisplaySearchResultsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button buttonSearch=(Button) view.findViewById(R.id.searchButton);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to search page", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();

                //replace nav_host_fragment (found in activity_main) with a DisplaySearchResultsFragment fragment
                fragTrans.replace(R.id.nav_host_fragment, new DisplaySearchResultsFragment());
                fragTrans.commit();
            }
        });

        return view;
    }
}