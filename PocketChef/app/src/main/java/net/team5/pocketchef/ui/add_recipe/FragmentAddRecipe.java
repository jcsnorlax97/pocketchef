package net.team5.pocketchef.ui.add_recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.MainActivity;
import net.team5.pocketchef.R;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentAddRecipe extends Fragment {

    // --- scroll view ---
    ScrollView vScrollView;

    // --- variables ---
    Button btnAddRecipe;
    EditText etRecipeName;

    // --- category spinner (selector) ---
    Spinner vCategorySpinner;

    // --- variables for ingredients ---
    MaterialButton btnAddIngredient;
    ChipGroup chipGroup;
    Chip chipItem;
    TextInputEditText tietIngredient;

    // --- variables for instructions (need to update in the future) ---
    EditText etRecipeInstructions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false); // create view based on fragment_add_recipe.xmll in layout

        // link view to variables

        // --- scroll view ---
        vScrollView = view.findViewById(R.id.vScrollView);
        vScrollView.post(new Runnable() {
            @Override
            public void run() {
                vScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        // --- recipe name and category ---
        etRecipeName = view.findViewById(R.id.etRecipeName);
        btnAddRecipe = view.findViewById(R.id.btnAddRecipe);

        // --- category spinner (selector) & setup ---
        vCategorySpinner = view.findViewById(R.id.vCategorySpinner);
        ArrayList<Category> categoryArrayList = MainActivity.manager.getCategories();
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryArrayList);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vCategorySpinner.setAdapter(categoryArrayAdapter);

        // --- ingredients ---
        btnAddIngredient = view.findViewById(R.id.btnAddIngredient);
        chipGroup = view.findViewById(R.id.chipGroup);
        tietIngredient = view.findViewById(R.id.tietIngredient);

        // --- instructions ---
        etRecipeInstructions = view.findViewById(R.id.etRecipeInstructions);

        // When 'Add Tag' button is clicked, we split text from input to tags form
        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient = tietIngredient.getText().toString();

                // Check if it is a duplicate
                if(isDuplicate(ingredient))
                {
                    // construct error message
                    String message = "Already Added";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check length
                if(ingredient.length() > 20)
                {
                    // construct error message
                    String message = "length is too large";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                LayoutInflater inflater = LayoutInflater.from(getContext());

                // create view for a chipItem
                chipItem = (Chip) inflater.inflate(R.layout.chip_item, null, false);
                chipItem.setText(ingredient); // replace default text with the current one.

                // add event handler for the close icon, i.e. when the close icon in the tag is clicked, execute the following.
                chipItem.setOnCloseIconClickListener(new View.OnClickListener() {

                    // View v here is the layout of a single chip
                    @Override
                    public void onClick(View v) {
                        // Remove tag when 'close' symbol is clicked
                        chipGroup.removeView(v);
                    }

                });

                // Add the chipItem to chipGroup
                chipGroup.addView(chipItem);

                // ensure the scrollview is scrolled to the very bottom when a new ingredient chip is added.
                vScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        // When 'btnAddRecipe' button is clicked, display what have been entered.
        // PS:  At the end, it will pass the data to the business layer to add the recipe information
        //      to the database.
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // --- local variables for AddRecipe ---
                RecipeObject newRecipe;
                String newRecipeName;
                Category newRecipeCategory;
                ArrayList<String> newRecipeInstructions = new ArrayList<>();
                ArrayList<Ingredient> newRecipeIngredients = new ArrayList<>();

                // --- name ---
                newRecipeName = etRecipeName.getText().toString();

                // --- name: quick ui validation ---
                if (newRecipeName.length() <= 0 || newRecipeName.length() > 20) {
                    // construct error message
                    String message = "Recipe length has to be 1-20 long";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- category (must be existing in db already) ---
                newRecipeCategory = (Category) vCategorySpinner.getSelectedItem();

                // --- category: quick ui validation ---
                if (newRecipeCategory == null)
                {
                    // construct error message
                    String message = newRecipeCategory.getCategoryName() + " does not exist. Please enter an existing category.";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- instructions ---
                // note:    This is a simple way for adding instructions.
                //          A more complicated way would have a button for adding new instruction text box, allow editing and deleting easily, and
                //          allow scrolling up and down when there are too much instructions and the screen doesn't fit.
                String recipeInstructionInOneString = etRecipeInstructions.getText().toString().trim();
                if (!recipeInstructionInOneString.equals("") && recipeInstructionInOneString.length() <= 100)
                {
                    String[] recipeInstructionArray = recipeInstructionInOneString.split("\n");
                    Collections.addAll(newRecipeInstructions, recipeInstructionArray);
                } else
                {
                    String message;
                    if (recipeInstructionInOneString.equals(""))
                        message = "Instruction has to be added";
                    else
                        message = "Instruction length has to be 1-100 long";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- ingredients ---
                StringBuilder result = new StringBuilder(); // for printing what has been entered

                // iterate over each chip in the chip group, get the chip item name, and add to the ingredientList
                for(int i=0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    // create ingredient object and add to the list
                    Ingredient ingredient = new Ingredient(chip.getText().toString());

                    // check if it is in DB, if not, add it
                    if (MainActivity.manager.getIngredient(ingredient.getIngredientName()) == null)
                        MainActivity.manager.addIngredient(ingredient);

                    // add to the ingredient list, which will be part of the new RecipeObject.
                    newRecipeIngredients.add(ingredient);

                    // append string for testing
                    result.append(chip.getText()).append(" ");
                }

                // --- ingredients: check if any were put ---
                if(newRecipeIngredients.size() == 0)
                {
                    // construct error message
                    String message = "Please enter at least 1 ingredient.";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- instructions: check if any were put ---
                if(newRecipeInstructions.size() == 0)
                {
                    // construct error message
                    String message = "Please enter at least 1 instruction.";

                    // show error message
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- create recipe & call DBManager to add the recipe ---
                newRecipe = new RecipeObject(newRecipeName, newRecipeCategory, newRecipeInstructions, newRecipeIngredients);
                MainActivity.manager.addRecipe(newRecipe);

                // show what has been entered; (just for testing; should remove it later)
                Toast.makeText(getContext(), newRecipe.getRecipeName() + " has been entered.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public boolean isDuplicate(String ingredName)
    {
        for(int i=0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            String chipName = chip.getText().toString().toLowerCase();
            if (chipName.equals(ingredName.toLowerCase()))
                return true;
        }
        return false;
    }
}
