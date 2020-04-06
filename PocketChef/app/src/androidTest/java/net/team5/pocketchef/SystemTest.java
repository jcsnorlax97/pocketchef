package net.team5.pocketchef;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SystemTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void systemTestAddRecipe() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_add_recipe), withContentDescription("Add Recipe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etRecipeName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Chicken"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.vCategorySpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.tietIngredient),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Chicken"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnAddIngredient), withText("Add Ingredient"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.tietIngredient), withText("Chicken"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Breadcrumbs"));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.tietIngredient), withText("Breadcrumbs"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnAddIngredient), withText("Add Ingredient"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        pressBack();

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etRecipeInstructions),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Preheat oven to 350c\nBread the chicken\nCook for 30 mins"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnAddRecipe), withText("Add Recipe"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                6),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.searchButton), withText("Search Recipe"),
                        childAtPosition(
                                allOf(withId(R.id.fragment_home),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.DisSearchRecyclerView),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3)),
                        4),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView), withText("Chicken"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Chicken")));
    }

    @Test
    public void systemTestDisplayRecipe() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.searchButton), withText("Search Recipe"),
                        childAtPosition(
                                allOf(withId(R.id.fragment_home),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.DisSearchRecyclerView),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3)),
                        0),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textView), withText("Poor Student Ramen"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewDirections), withText("Step 1: Boil water somehow? Probably cannot afford a kettle\n\nStep 2: Wash a bowl since you have no clean ones\n\nStep 3: Open the 50 cent ramen package\n\nStep 4: Put all contents into bowl\n\nStep 5: Pour hot water and wait 5 minutes\n\nStep 6: Enjoy!\n\n\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
