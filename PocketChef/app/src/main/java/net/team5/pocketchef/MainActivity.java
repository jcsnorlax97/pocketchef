package net.team5.pocketchef;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import net.team5.pocketchef.Database.RecipeDatabase;
import net.team5.pocketchef.objects.Recipe.Ingredients;
import net.team5.pocketchef.objects.Recipe.Instructions;
import net.team5.pocketchef.objects.Recipe.Recipe;
//import net.team5.pocketchef.ui.search.SearchFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView searchRecipes;
    ArrayAdapter<Recipe> adapter;

    private static final String TAG = "MainActivity";

    //VERSION 2.0 SEARCH CODE VARIABLES
    //private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    //private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Log.d(TAG, "onCreate: Started.");

        //Version 2.0 search code?
        /*
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.VPcontainer);

        //setup the pager
        setupViewPager(mViewPager);
        */

        //RecipeDatabase searchResults = new RecipeDatabase();

        //this is the pointer to the full database
        RecipeDatabase fullDatabase = new RecipeDatabase();

        //John's stuff for tesing
        ArrayList<String> ingred = new ArrayList<String>();
        ingred.add("ingred1");
        ingred.add("ingred2");
        ingred.add("ingred3");
        ingred.add("ingred4");

        ArrayList<String> instruct = new ArrayList<String>();
        instruct.add("instruct1");
        instruct.add("instruct2");
        instruct.add("instruct3");
        instruct.add("instruct4");



        fullDatabase.addRecipe(new Recipe("name1", "cat1", new Ingredients(ingred), new Instructions(instruct)));

        searchRecipes = (ListView) findViewById(R.id.searchRecipesView);

        adapter = new ArrayAdapter<Recipe>(
                MainActivity.this,
                android.R.layout.simple_expandable_list_item_1,
                fullDatabase.getRecipes()//this is where we put the results of the search after a queury has been processed in the database layer
        );

        searchRecipes.setAdapter(adapter);
    }



    /**
     * This is the method that deals with the seach button in the top-right of the screen
     *
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.searchRecipes);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /*THIS STUFF GOES WITH THE POSSIBLE VERSION 2.0 STUFF AS STATED ABOVE
    public void setViewPager(int fragementNumber){
        mViewPager.setCurrentItem(fragementNumber);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(), "Search Fragment");
        viewPager.setAdapter(adapter);
    }
    */

}
