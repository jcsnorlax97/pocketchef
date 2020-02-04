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

import net.team5.pocketchef.R;

public class FragmentAddRecipe extends Fragment {

    // --- variables ---
    Button btnAddRecipe;
    EditText etRecipeName;
    EditText etRecipeCategory;
    String recipeName;
    String recipeCategory;

    // --- a testing material chip ---
    Chip chipItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false); // create view based on fragment_add_recipe.xmll in layout

        // link view to variables
        etRecipeName = view.findViewById(R.id.etRecipeName);
        etRecipeCategory = view.findViewById(R.id.etRecipeCategory);
        btnAddRecipe = view.findViewById(R.id.btnAddRecipe);
        chipItem = view.findViewById(R.id.chipItem);

        // add event handler for onClick event
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            recipeName = etRecipeName.getText().toString();
            recipeCategory = etRecipeCategory.getText().toString();

            // show what has been entered
            Toast.makeText(getContext(), recipeName + " " + recipeCategory + " is entered.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
