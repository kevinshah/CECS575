package com.example.kevin.unisalestorm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kevin.unisalestoem.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;



public class mainPage extends Activity {

    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        new RemoteDataTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void displayItem(View view) {

        ParseObject itemDescription = new ParseObject("ItemDatabase");

        final Context context = this;;

        itemDescription.put("Category","Electronic" );
        itemDescription.put("Name","Mac Book Pro" );
        itemDescription.put("Description","2 months old" );
        itemDescription.put("Price","$1100" );

        itemDescription.put("Category","Furniture");
        itemDescription.put("Name","Desk" );
        itemDescription.put("Description","4 years old" );
        itemDescription.put("Price","$100" );

        itemDescription.put("Category","Other" );
        itemDescription.put("Name","Mountain Bike" );
        itemDescription.put("Description","Used 3 months" );
        itemDescription.put("Price","$500" );

        itemDescription.put("Category","Other" );
        itemDescription.put("Name","Coachella 2016" );
        itemDescription.put("Description","I'm selling two tickets for $ 650 each" );
        itemDescription.put("Price","$650" );

        itemDescription.saveInBackground();

            Intent intent = new Intent(this, mainPage.class);
            startActivity(intent);


    }

    public void addItem(View view) {
        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }



    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(mainPage.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Kevin ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "ItemDatabase" in Parse.com

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ItemDatabase");
            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (ParseException e) {
                //  Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into an ArrayAdapter
            adapter = new ArrayAdapter<String>(mainPage.this,
                    R.layout.listview_item);
            // Retrieve object "name" from Parse.com database
            for (ParseObject ItemDatabase : ob) {
                adapter.add((String) ItemDatabase.get("itemName"));
            }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // Send single item click data to SingleItemView Class
                    Intent i = new Intent(mainPage.this,
                            SingleItemView.class);
                    // Pass data "name" followed by the position
                    i.putExtra("name", ob.get(position).getString("name")
                            .toString());
                    // Open SingleItemView.java Activity
                    startActivity(i);
                }
            });
        }
    }



}
