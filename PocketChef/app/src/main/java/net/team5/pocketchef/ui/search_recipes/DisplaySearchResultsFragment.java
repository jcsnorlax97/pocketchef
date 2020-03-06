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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.team5.pocketchef.Business.Objects.Recipe.Ingredients;
import net.team5.pocketchef.Business.Objects.Recipe.Instructions;
import net.team5.pocketchef.Business.Objects.Recipe.Recipe;


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

    //this is the variable that is used to show the recipes to the user
    //thus everything in this var should be a recipe that the user searched for/is relevant
    ArrayList<RecipeItem> recipeList = new ArrayList<>();

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

        /**
         * EXAMPLE LIST
         * */
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground, getRecipeTemp()));
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground,new Recipe("Test 2", "testing 1", new Ingredients(), new Instructions())));
        recipeList.add(new RecipeItem(R.drawable.temp_recipe_image_foreground,new Recipe("Test 3", "testing 1", new Ingredients(), new Instructions())));


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
        ArrayList<RecipeItem> filteredList = new ArrayList<>();

        for(RecipeItem item: recipeList){
            if(item.getRecipeObj().recipeName.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

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
        fragTrans.replace(R.id.nav_host_fragment, new DisplayFragment(recipeList.get(position).getRecipeObj()));

        fragTrans.commit();
    }

    /* Strictly a method used to create, and then demonstrate what displaying a recipe would look like.
    This will be deleted in iteration 3.*/
    private Recipe getRecipeTemp()
    {
        ArrayList<String> ingreds = new ArrayList<String>();
        ingreds.add("1-2 pounds chicken tenders or 2 large boneless skinless chicken breasts sliced into 1-inch thick strips");
        ingreds.add("1 1/2 cups all purpose flour");
        ingreds.add("3/4 teaspoon salt");
        ingreds.add("1/2 teaspoon black pepper");
        ingreds.add("1 egg beaten with 2 tablespoons water");
        ingreds.add("vegetable oil for frying");

        ArrayList<String> instruct = new ArrayList<String>();
        instruct.add("Fill a 9-inch cast iron or standard skillet with about 2 inches of oil. Start heating it over medium-high heat while you bread the chicken. (Keep an eye on it!)");
        instruct.add("In a large bowl, mix the flour, salt and pepper.");
        instruct.add("In another large bowl, beat the egg and water");
        instruct.add("Dredge the chicken in the flour, coating well. Shake off excess flour and dip in the egg, the back in the flour.");
        instruct.add("Set the chicken to the side to rest for about 5 minutes. (This helps the coating stick better)");
        instruct.add("Check your oil temperature with a candy thermometer if necessary (you should be around 365-375 degrees) or drop in a little bit of flour–if it sizzles immediately, you can add one piece of chicken");
        instruct.add("If the chicken sizzles, add about 5 pieces at a time and cook until golden brown on that side–about 8 to 10 minutes or so.");
        instruct.add("Turn, and repeat until all brown.");
        instruct.add("Cook the rest of the chicken in batches.");
        instruct.add("Transfer to a paper towel lined plate and sprinkle with a little more salt if needed.");

        return new Recipe("Breaded Chicken Tenders","American", ingreds, instruct);
    }
    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
