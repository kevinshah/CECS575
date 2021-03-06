package com.example.kevin.unisalestorm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kevin.unisalestoem.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;



public class mainPage extends Activity {


    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<ListHelper> listHelper = null;
    String passedArg;
    String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passedArg = getIntent().getExtras().getString("username");

       // Toast.makeText(mainPage.this, "UserName From Login" +passedArg, Toast.LENGTH_LONG).show();
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


    public void myUploads(View view) {
        Intent intent = new Intent(this, myUploads.class);
        intent.putExtra("username", passedArg);
        startActivity(intent);
    }

    public void search(View view) {

        searchQuery = ((EditText)findViewById(R.id.searchField)).getText().toString();
        Intent intent = new Intent(this, Search.class);
        intent.putExtra("username", passedArg);
        intent.putExtra("searchQuery", searchQuery);
        startActivity(intent);
    }


    public void addItem(View view) {
        Intent intent = new Intent(this, addItem.class);
        intent.putExtra("username", passedArg);
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
            mProgressDialog.setTitle("UniSaleStorm");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            // Create the array
            listHelper = new ArrayList<ListHelper>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ItemDatabase");
                query.orderByDescending("_created_at");

                ob = query.find();
                for (ParseObject itemDatabase : ob) {
                    // Locate images in flag column
                    ParseFile image = (ParseFile) itemDatabase.get("photo");

                    ListHelper map = new ListHelper();
                    map.setItemName((String) itemDatabase.get("itemName"));
                    map.setCategory((String) itemDatabase.get("category"));
                    map.setDescription((String) itemDatabase.get("description"));
                    map.setFlag(image.getUrl());
                    listHelper.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(mainPage.this, listHelper, passedArg);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }




}
