package net.team5.pocketchef.ui.search_recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import net.team5.pocketchef.R;
import net.team5.pocketchef.ui.home.HomeViewModel;

import static android.content.ContentValues.TAG;

/**
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 2
 * Feb 25 2020
 */
public class SearchRecipesFragment extends Fragment {

    private static final String TAG = "Search Recipes Fragment";

    private HomeViewModel homeViewModel;
    private String queryString;//the string that the user want to search the database with


    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public SearchRecipesFragment(){

    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(net.team5.pocketchef.ui.home.HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_display_search_results, container, false);


        Log.d(TAG, "onCreateView: started.");



        //return the view
        return view;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
