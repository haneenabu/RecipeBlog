package com.example.epicodus.recipeblog.ui;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;


        import com.example.epicodus.recipeblog.Constants;
        import com.example.epicodus.recipeblog.R;
        import com.example.epicodus.recipeblog.models.Recipe;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.squareup.picasso.Picasso;


        import org.parceler.Parcels;

        import butterknife.Bind;
        import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;


    @Bind(R.id.recipeNameTextView) TextView mRecipeNameText;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;
    @Bind(R.id.websiteTextView) TextView mWebsite;
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
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));

        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext()).load(mRecipe.getmImage()).resize(MAX_WIDTH,MAX_HEIGHT).centerCrop().into(mImageView);

        mRecipeNameText.setText(mRecipe.getmRecipeName());
        mRatingTextView.setText(Integer.toString(mRecipe.getmRating()));
        mTimeTextView.setText(Integer.toString(mRecipe.getmTotalTime()));
        mWebsite.setOnClickListener(this);
        mSaveRecipeButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        if (view == mWebsite){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com/search?q=" + mRecipe.getmRecipeName()));
            startActivity(webIntent);
        }
        if (view == mSaveRecipeButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPE)
                    .child(uid);

            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);

//            recipeRef.push().setValue(mRecipe);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
