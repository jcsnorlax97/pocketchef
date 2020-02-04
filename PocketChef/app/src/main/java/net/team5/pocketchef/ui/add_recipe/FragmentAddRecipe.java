package net.team5.pocketchef.ui.add_recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import net.team5.pocketchef.MainActivity;
import net.team5.pocketchef.R;

import java.util.ArrayList;

public class FragmentAddRecipe extends Fragment {

    // --- variables ---
    Button btnAddRecipe;
    EditText etRecipeName;
    EditText etRecipeCategory;
    String recipeName;
    String recipeCategory;

    // --- a testing material chip ---
    Chip chipItem;

    // --- variables for ingredients ---
    MaterialButton btnAddIngredient;
    MaterialButton btnCheckAllIngredients;
    ChipGroup chipGroup;
    TextInputEditText tietIngredient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false); // create view based on fragment_add_recipe.xmll in layout

        // link view to variables
        etRecipeName = view.findViewById(R.id.etRecipeName);
        etRecipeCategory = view.findViewById(R.id.etRecipeCategory);
        btnAddRecipe = view.findViewById(R.id.btnAddRecipe);

        chipItem = view.findViewById(R.id.chipItem);

        btnAddIngredient = view.findViewById(R.id.btnAddIngredient);
        btnCheckAllIngredients = view.findViewById(R.id.btnCheckAllIngredients);
        chipGroup = view.findViewById(R.id.chipGroup);
        tietIngredient = view.findViewById(R.id.tietIngredient);

        // When 'Add Tag' button is clicked, we split text from input to tags form
        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient = tietIngredient.getText().toString();
                LayoutInflater inflater = LayoutInflater.from(getContext()); // (Not sure if this is the correct way to do it.)

                // create view for a chipItem
                chipItem = (Chip) inflater.inflate(R.layout.chip_item, null, false); // (Not sure if this is the correct way to do it.)
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

            }
        });

        // When 'btnCheckAllIngredients' button is clicked, it checks all ingredients that have been added.
        btnCheckAllIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // iterate over each chip in the group, get the chip item name, and append to result.
                for(int i=0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    chip.setChecked(true);

                }

            }
        });

        // When 'btnAddRecipe' button is clicked, display what have been entered.
        // PS:  At the end, it will pass the data to the business layer to add the recipe information
        //      to the database.
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recipeName = etRecipeName.getText().toString();
                recipeCategory = etRecipeCategory.getText().toString();

                ArrayList<String> ingredientList = new ArrayList<>();
                StringBuilder result = new StringBuilder(""); // (just for testing; should remove it later)

                // iterate over each chip in the chip group, get the chip item name, and add to the ingredientList
                for(int i=0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isChecked()) {
                        ingredientList.add(chip.getText().toString());
                        result.append(chip.getText()).append(" "); // (just for testing; should remove it later)
                    }
                }

                // show what has been entered; (just for testing; should remove it later)
                Toast.makeText(getContext(), recipeName + " " + recipeCategory + " " + result.toString() + " is entered.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
