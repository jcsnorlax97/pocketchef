package net.team5.pocketchef.tests.system;

import android.test.suitebuilder.annotation.LargeTest;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import net.team5.pocketchef.MainActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(AndroidJUnit4.class)
@LargeTest
public class SystemTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {

    }
}
