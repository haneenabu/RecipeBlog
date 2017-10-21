package com.example.epicodus.recipeblog.ui;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;


        import com.example.epicodus.recipeblog.R;
        import com.example.epicodus.recipeblog.models.Recipe;
        import com.squareup.picasso.Picasso;


        import org.parceler.Parcels;

        import butterknife.Bind;
        import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment {

    @Bind(R.id.recipeImageView) ImageView mImageView;
    @Bind(R.id.recipeNameTextView) TextView mRecipeNameText;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.saveRecipeButton) TextView mSaveRecipeButton;

    private Recipe mRecipe;

    public static RecipeDetailFragment newInstance(Recipe recipe) {

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mRecipe= Parcels.unwrap(getArguments().getParcelable("recipe"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mRecipe.getmImage()).into(mImageView);

        mRecipeNameText.setText(mRecipe.getmRecipeName());
        mRatingTextView.setText(mRecipe.getmRating());
        mTimeTextView.setText(mRecipe.getmTotalTime());
        mWebsiteTextView.setText("www.allrecipies.com/");

        return view;
    }

}
