package net.team5.pocketchef.ui.search_recipes;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This was created using the tutorial(s) from https://www.youtube.com/watch?v=17NbUcEts9c&list=PLrnPJCHvNZuBtTYUuc5Pyo4V7xZ2HNtf4&index=2
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 2
 * Feb 25 2020
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements Filterable
{

    private List<RecipeObject> mRecipeList;

    //extra list for searching
    private List<RecipeObject> recipeListFull;

    private OnItemClickListener mListener;//listener for the click

    ///////////////////////////////////////////////////////////////////////////
    // CLASSES
    ///////////////////////////////////////////////////////////////////////////

    public static class RecipeViewHolder extends RecyclerView.ViewHolder
    {

        public TextView mTextViewName;
        public TextView mTextViewCat;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.recipeNameTextView);
            mTextViewCat = itemView.findViewById(R.id.recipeCategoryTextView);
            //mTextViewCat = itemView.findViewById(R.id.reci);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();

                        //make sure the position is vaild
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CLASSES
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public RecipeAdapter(ArrayList<RecipeObject> recipeList)
    {

        mRecipeList = recipeList;

        recipeListFull = new ArrayList<>(recipeList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);

        RecipeViewHolder rvh = new RecipeViewHolder(v, mListener);
        return rvh;
    }

    /**
     * set the image and the text of the cardview for displaying search results
     * */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position)
    {
        RecipeObject currentItem = mRecipeList.get(position);

        holder.mTextViewName.setText(currentItem.getRecipeName());
        Category recipeCategory = currentItem.getRecipeCategory();
        if (recipeCategory != null)
            holder.mTextViewCat.setText(recipeCategory.getCategoryName());
        else
            holder.mTextViewCat.setText("N/A");
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    /**
     * method to access the listener when a click happens
     * */
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    /**
     * accesser method to set the listener
     * */
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public Filter getFilter() {
        return recipeFilter;
    }

    private Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            //this is where we return our filter results
            List<RecipeObject> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0)
            {
                //show everything
                filteredList.addAll(recipeListFull);
            }
            else
            {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(RecipeObject item: recipeListFull)
                {
                    if(item.getRecipeName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            mRecipeList.clear();
            mRecipeList.addAll((List)filterResults.values);//add
            notifyDataSetChanged();
        }
    };

    /**
     * Set the recipe list to display only relevent search results
     * */
    public void filterList(ArrayList<RecipeObject> filterList)
    {
        mRecipeList = filterList;
        notifyDataSetChanged();
    }

    /**
     * @return the list of recipe objects that are filtered
     * */
    public List<RecipeObject> getFilteredList()
    {
        return mRecipeList;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
