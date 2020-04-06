package net.team5.pocketchef.ui.recipe_display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.ArrayList;

public class DisplayViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> ingredientList;
    private MutableLiveData<String> directionList;
    private RecipeObject rec;
    private ArrayList<String> instruc;
    private ArrayList<String> ingreds;

    public DisplayViewModel() {

    }

    public void setValues( RecipeObject tempRecipe)
    {
        boolean noInstructions = false;
        boolean noIngredients = false;
        rec=tempRecipe;
        if(rec.getRecipeInstructions() !=null)
            instruc = rec.getRecipeInstructions();
        else
            noInstructions = true;
        if(rec.getRecipeIngredients() != null)
        {
            //convert array of Ingredients to array of strings
            ArrayList<String> ingredsList = new ArrayList<>();
            for(int i = 0; i < rec.getRecipeIngredients().size(); i++)
            {
                Ingredient currIngred = rec.getRecipeIngredients().get(i);
                if(currIngred != null)
                    ingredsList.add(currIngred.getIngredientName());
            }
            ingreds = ingredsList;
        }
        else
            noIngredients = true;

        String instructions = "";
        if(!noInstructions)
        {
            for (int i = 0; i < instruc.size(); i++)
                instructions += "Step " + (i + 1) + ": " + instruc.get(i) + "\n\n";
        }
        else
            instructions = "There are no instructions for this recipe.";

        String ingredients = "";
        if(!noIngredients)
        {
            for (int i = 0; i < ingreds.size(); i++)
                ingredients += "- " + ingreds.get(i) + "\n";
        }
        else
            ingredients = "There are no ingredients for this recipe.";

        name = new MutableLiveData<>();
        name.setValue(rec.getRecipeName());

        ingredientList = new MutableLiveData<>();
        ingredientList.setValue(ingredients+"\n\n");

        directionList = new MutableLiveData<>();
        directionList.setValue(instructions+"\n\n");

    }

    public LiveData<String> getRName() {
        return name;
    }

    public LiveData<String> getDirections() {
        return directionList;
    }

    public LiveData<String> getIngredients() {
        return ingredientList;
    }
}
