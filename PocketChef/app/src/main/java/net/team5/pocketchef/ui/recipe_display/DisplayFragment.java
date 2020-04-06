package net.team5.pocketchef.ui.recipe_display;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.R;

public class DisplayFragment extends Fragment{

    private DisplayViewModel displayViewModel;
    private RecipeObject recipe;

    public DisplayFragment(RecipeObject recipeTemp)
    {
        recipe=recipeTemp;

    }

    public DisplayFragment()
    {
        recipe=null;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        displayViewModel =
                ViewModelProviders.of(this).get(DisplayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_display_recipe, container, false);

        if(this.recipe != null)
            displayViewModel.setValues(this.recipe);

        final TextView textView2 = root.findViewById(R.id.textView);
        displayViewModel.getRName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView2.setText(s);
            }
        });


        final TextView ingredientTitle = root.findViewById(R.id.textViewTitleIngredients);
        ingredientTitle.setText("Ingredients");
        final TextView ingredients = root.findViewById(R.id.textViewIngredients);
        displayViewModel.getIngredients().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ingredients.setText(s);
            }
        });


        final TextView directionsTitle = root.findViewById(R.id.textViewTitleDirections);
        directionsTitle.setText("Instructions");
        final TextView directions = root.findViewById(R.id.textViewDirections);
        displayViewModel.getDirections().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                directions.setText(s);
            }
        });

        return root;
    }
}




