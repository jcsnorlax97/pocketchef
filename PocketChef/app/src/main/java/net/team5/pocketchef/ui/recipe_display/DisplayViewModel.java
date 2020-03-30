package net.team5.pocketchef.ui.recipe_display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.team5.pocketchef.Business.Objects.Recipe.Ingredients;
import net.team5.pocketchef.Business.Objects.Recipe.Instructions;
import net.team5.pocketchef.Business.Objects.Recipe.Recipe;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.ArrayList;

public class DisplayViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> name;
    private MutableLiveData<String> discription;
    private MutableLiveData<String> recipe;
    private MutableLiveData<String> ingredientList;
    private MutableLiveData<String> directionList;
    private RecipeObject rec;
    private ArrayList<String> instruc;
    private ArrayList<String> ingreds;
    public DisplayViewModel() {
        /*ArrayList<String> ingreds = new ArrayList<String>();
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

        Recipe rec = new Recipe("Breaded Chicken Tenders","American", new Ingredients(ingreds), new Instructions(instruct));*/


        /*String instructions = "";
        for(int i = 0; i< instruct.size(); i++)
            instructions+= "Step "+(i+1)+": "+instruct.get(i)+"\n\n";

        String ingredients = "";
        for(int i = 0; i< ingreds.size(); i++)
            ingredients+= "- "+ingreds.get(i)+"\n";

        String discription;
        discription=("This recipe will yield very tasty, golden, breaded chicken fingers.");
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        name = new MutableLiveData<>();
        name.setValue(rec.recipeName+"\n\n"+discription);

        ingredientList = new MutableLiveData<>();
        ingredientList.setValue(ingredients+"\n\n");

        directionList = new MutableLiveData<>();
        directionList.setValue(instructions+"\n\n");
*/
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
                ingredsList.add(rec.getRecipeIngredients().get(i).getIngredientName());
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

        String discription;
        discription=("There is no description for this recipe.");// ADD THIS INTO THE CODE OF JOHNS

        name = new MutableLiveData<>();
        name.setValue(rec.getRecipeName()+"\n\n"+discription);

        ingredientList = new MutableLiveData<>();
        ingredientList.setValue(ingredients+"\n\n");

        directionList = new MutableLiveData<>();
        directionList.setValue(instructions+"\n\n");

    }
    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getRName() {
        return name;
    }

    public LiveData<String> getDiscription() {
        return discription;
    }

    public LiveData<String> getDirections() {
        return directionList;
    }

    public LiveData<String> getIngredients() {
        return ingredientList;
    }
}
