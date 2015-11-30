package com.example.kevin.unisalestorm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.*;


import com.example.kevin.unisalestoem.R;
import com.parse.ParseObject;

public class addItem extends AppCompatActivity {

    private Spinner categories;
    private EditText itemName;
    private EditText description;
    private EditText price;
    private EditText contactNumber;
    private EditText placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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


    public void signUp(View view) {

        categories = (Spinner) findViewById(R.id.categories);
        itemName   = (EditText)findViewById(R.id.itemName);
        description   = (EditText)findViewById(R.id.description);
        price   = (EditText)findViewById(R.id.price);
        contactNumber   = (EditText)findViewById(R.id.contactNumber);
        placeHolder   = (EditText)findViewById(R.id.placeHolder);

        ParseObject gameScore = new ParseObject("ItemDatabase");
        String saveContactNumber = contactNumber.getText().toString();
        String saveItemName = itemName.getText().toString();
        String saveDescription = description.getText().toString();
        String savePrice = price.getText().toString();
        String saveCategory = (String) categories.getSelectedItem();
       // String re_p = repassword.getText().toString();
        final Context context = this;;

        final String EditEmail = price.getText().toString();

            gameScore.put("category", saveCategory);
            gameScore.put("itemName", saveItemName);
            gameScore.put("description", saveDescription);
            gameScore.put("price", savePrice);
            gameScore.put("contactNumber", saveContactNumber);
        gameScore.saveInBackground();

            Toast.makeText(context, "Improper Login" +saveCategory, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, mainPage.class);
            startActivity(intent);

    }


}
