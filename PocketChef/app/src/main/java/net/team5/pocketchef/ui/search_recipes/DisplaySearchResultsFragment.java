package net.team5.pocketchef.ui.search_recipes;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.team5.pocketchef.R;
import net.team5.pocketchef.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 2
 * Feb 25 2020
 *
 * main class that handles switching to other fragments after a search was preformed (acts as a 'MainActivity' sortof)
 */
public class DisplaySearchResultsFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final String TAG = "Display Search Results Fragment";

    private RecyclerView searchResults;//this is the widget that displays the recipes in a list
    private RecipeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    //this is the variable that is used to show the recipes to the user
    //thus everything in this var should be a recipe that the user searched for/is relevant
    ArrayList<RecipeItem> recipeList = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public DisplaySearchResultsFragment(){
        ArrayList<RecipeItem> recipeList = new ArrayList<>();
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

        searchResults = (RecyclerView) view.findViewById(R.id.DisSearchRecyclerView);

        Log.d(TAG, "onCreateView: started.");

        /**
         * EXAMPLE LIST
         * */
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground,"This is where we need to add the recipes to be displayed"));
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground,"Test 2"));
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground,"Test 3"));

        //set adapters and stuff
        buildRecyclerView(view);

        //deal with clicks on displayed recipes
        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
                displayRecipe(position);
            }
        });

        //set up variables for the search bar
        EditText searchBar = view.findViewById(R.id.editText);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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
    private void filter(String text){
        ArrayList<RecipeItem> filteredList = new ArrayList<>();

        for(RecipeItem item: recipeList){
            if(item.getRecipeName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    /**
     * Sets up the recycler view and stuff
     * */
    private void buildRecyclerView(View view) {
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
        //TO DO:
    }

    /**
     * THIS IS A TEMP FUNCTION TO TEXT THE CLICKING OF A ITEM IN THE RECYCLER VIEW (SEARCH LIST)
     * */
    public void changeItem(int position, String text){
        recipeList.get(position).changeRecipeName(text);
        adapter.notifyItemChanged(position);
    }

    /* THIS IS HERE INCASE WE WANT TO ADD A SEARCH ICON IN THE TOP RIGHT OF THE APP, ALTHOUGH THE CODE ISN'T WORKING RIGHT NOW WHEN IT SHOULD BE (ICON DOESN'T GET DISPLAYED)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_recipes_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

     */

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
