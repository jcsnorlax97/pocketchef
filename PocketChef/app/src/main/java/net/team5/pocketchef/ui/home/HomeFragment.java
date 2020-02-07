package net.team5.pocketchef.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import net.team5.pocketchef.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        Button buttonSearch=(Button) view.findViewById(R.id.searchButton);
//
//        buttonSearch.setOnClickListener(new View.OnClickListener(){
//
//
//            @Override
//            public void onClick(View v){
//
//               FragmentTransaction fr=getFragmentManager().beginTransaction();
//               fr.replace(R.id.fragment_home,new DashboardFragment());
//               fr.commit();
//            }
//
//
//        });

        return view;
    }
}