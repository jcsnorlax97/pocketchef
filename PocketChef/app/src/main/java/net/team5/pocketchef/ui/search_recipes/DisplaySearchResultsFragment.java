package net.team5.pocketchef.ui.search_recipes;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.MainActivity;
import net.team5.pocketchef.R;
import net.team5.pocketchef.ui.home.HomeViewModel;
import net.team5.pocketchef.ui.recipe_display.DisplayFragment;

import java.util.ArrayList;

/**
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 2
 * Feb 25 2020
 *
 * main class that handles switching to other fragments after a search was preformed (acts as a 'MainActivity' sortof)
 */
public class DisplaySearchResultsFragment extends Fragment
{

    private HomeViewModel homeViewModel;
    private static final String TAG = "Display Search Results Fragment";

    private RecyclerView searchResults;//this is the widget that displays the recipes in a list
    private RecipeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private CheckBox searchCat;
    private CheckBox searchIngred;

    private boolean searchRecipeIngredients = false;
    private boolean searchRecipeCategories = false;

    //this is the variable that is used to show the recipes to the user
    //thus everything in this var should be a recipe that the user searched for/is relevant
    ArrayList<RecipeObject> recipeList = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public DisplaySearchResultsFragment(){
        recipeList = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(net.team5.pocketchef.ui.home.HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_display_search_results, container, false);

        searchResults = (RecyclerView) view.findViewById(R.id.DisSearchRecyclerView);

        Log.d(TAG, "onCreateView: started.");

        //fetch stuff from the manager incase there where changes since the last time we where here
        recipeList = MainActivity.manager.getRecipes();

        //set adapters and stuff
        buildRecyclerView(view);

        //deal with clicks on displayed recipes
        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                displayRecipe(position);
            }
        });

        //manage the checkbox for searching categories
        searchCat = (CheckBox)view.findViewById(R.id.searchCatCB);
        searchCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchCat.isChecked()){
                    searchRecipeCategories = true;
                    searchIngred.setChecked(false);
                    searchRecipeIngredients = false;
                    Toast.makeText(getContext(), "Searching through categories", Toast.LENGTH_SHORT).show();
                }
                else{
                    searchRecipeCategories = false;
                    Toast.makeText(getContext(), "Not searching through categories", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //manage the checkbox for searching categories
        searchIngred = (CheckBox)view.findViewById(R.id.searchIngredients);
        searchIngred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchIngred.isChecked()){
                    searchRecipeIngredients = true;
                    searchCat.setChecked(false);
                    searchRecipeCategories = false;
                    Toast.makeText(getContext(), "Searching through categories", Toast.LENGTH_SHORT).show();
                }
                else{
                    searchRecipeIngredients = false;
                    Toast.makeText(getContext(), "Not searching through categories", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //set up variables for the search bar
        EditText searchBar = view.findViewById(R.id.editText);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        //return the view
        return view;
    }

    /**
     * this deals with the string the user is using to search for recipes
     * gets called everytime the string updates
     * */
    private void filter(String text)
    {
        //pass on the search to the DB manager
        ArrayList<RecipeObject> filteredList = MainActivity.manager.search(text, searchRecipeCategories, searchRecipeIngredients);

        //pass the search results to the adapter
        adapter.filterList(filteredList);
    }

    /**
     * Sets up the recycler view and stuff
     * */
    private void buildRecyclerView(View view)
    {
        searchResults = view.findViewById(R.id.DisSearchRecyclerView);
        searchResults.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecipeAdapter(recipeList);

        searchResults.setLayoutManager(layoutManager);
        searchResults.setAdapter(adapter);
    }

    /**
     * Function to display the recipe that the user clicked on from the recylcer view (the search list)
     * */
    public void displayRecipe(int position){
        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
        fragTrans.replace(R.id.nav_host_fragment, new DisplayFragment(adapter.getFilteredList().get(position)));

        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
