package com.example.kevin.unisalestorm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.unisalestoem.R;

public class SingleItemViewSearch extends Activity {
    // Declare Variables
    String itemName;
    String category;
    String description;
    String photo;
    String position;
    String username;
    ImageLoader imageLoader = new ImageLoader(this);
    TextView txtItemName;
    TextView txtCategory;
    TextView txtDescription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.activity_single_item_view_search);

        Intent i = getIntent();
        // Get the result of rank
        itemName = i.getStringExtra("itemDatabase");
        // Get the result of country
        category = i.getStringExtra("category");
        // Get the result of population
        description = i.getStringExtra("description");
        // Get the result of flag
        photo = i.getStringExtra("flag");
        username = i.getStringExtra("username");


        // Locate the TextViews in singleitemview.xml
        txtItemName = (TextView) findViewById(R.id.rank);
        txtCategory = (TextView) findViewById(R.id.country);
        txtDescription = (TextView) findViewById(R.id.population);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.flag);

        // Set results to the TextViews
        txtItemName.setText(itemName);
        txtCategory.setText(category);
        txtDescription.setText(description);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(photo, imgflag);
    }
}