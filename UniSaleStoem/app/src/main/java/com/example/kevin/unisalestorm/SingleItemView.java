package com.example.kevin.unisalestorm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.unisalestoem.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SingleItemView extends Activity {
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
		setContentView(R.layout.singleitemview);

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

	public void contactSeller (View view){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ItemDatabase");
		query.whereEqualTo("itemName", itemName);
		query.whereEqualTo("description", description);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {

			String filelocation= Environment.getExternalStorageDirectory()+"/Application.xls";
				ParseObject user_pass = arg0.get(0);
				String password_user = user_pass.getString("username");
			Log.i("Send email", "");
			String[] TO = {password_user};
			String[] CC = {""};
			Intent emailIntent = new Intent(Intent.ACTION_SEND);

			emailIntent.setData(Uri.parse("mailto:"));
			emailIntent.setType(".xls application/x-excel");
			emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
			emailIntent.putExtra(Intent.EXTRA_CC, CC);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UniSaleStorm: "+itemName+".");
			emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I'm interested in the" + itemName+". I can be reached at ___________.");

			try {
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				finish();
				//   Log.i("Finished sending email...", "");
			}
			catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(SingleItemView.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
			}


			}
		});
		}





}