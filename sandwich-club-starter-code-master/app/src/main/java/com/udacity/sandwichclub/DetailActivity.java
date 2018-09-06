package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView mTvAlsoKnowTextView, mTvOriginTextView, mTvDescTextView, mTvIngredientsTextView;
    ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        mTvAlsoKnowTextView = (TextView)findViewById(R.id.also_known_tv);
        mTvOriginTextView = (TextView)findViewById(R.id.origin_tv);
        mTvDescTextView = (TextView)findViewById(R.id.description_tv);
        mTvIngredientsTextView = (TextView)findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
           // Toast.makeText(this, "rrrrrrrrrrrr", Toast.LENGTH_SHORT).show();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();

    }



    private void populateUI(Sandwich sandwich) {

        if(sandwich.getPlaceOfOrigin().isEmpty())
            mTvOriginTextView.setText("NO Data !!");
        else
            mTvOriginTextView.setText(sandwich.getPlaceOfOrigin());


        if(sandwich.getDescription().isEmpty())
            mTvDescTextView.setText("NO Data !!");
        else
            mTvDescTextView.setText(sandwich.getDescription());

        if(sandwich.getAlsoKnownAs().isEmpty())
            mTvAlsoKnowTextView.setText("NO Data !!");
        else
            mTvAlsoKnowTextView.setText(buildString(sandwich.getAlsoKnownAs()));


        if(sandwich.getIngredients().isEmpty())
            mTvIngredientsTextView.setText("NO Data !!");
        else
            mTvIngredientsTextView.setText(buildString(sandwich.getIngredients()));

    }

    StringBuilder buildString(List<String> array){
        StringBuilder stringbuilder = new StringBuilder();
        for(int i=0; i<array.size(); i++)
            stringbuilder.append(array.get(i) + ", ");
        return stringbuilder;
    }
}
