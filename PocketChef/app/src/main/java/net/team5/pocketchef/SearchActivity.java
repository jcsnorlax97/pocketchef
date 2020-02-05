package net.team5.pocketchef;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

//import net.team5.pocketchef.ui.search.SearchFragment;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    /*
    private  SectionsStatePagerAdapter mSectionsSatePagerAdapter;
    private ViewPager mViewPager;
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);

        Log.d(TAG, "onCreate: started.");

        /*
        mSectionsSatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        */
    }

    /*
    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(), "Search Fragment");
    }

     */
}
