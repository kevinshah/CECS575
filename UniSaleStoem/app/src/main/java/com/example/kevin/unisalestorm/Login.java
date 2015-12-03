package com.example.kevin.unisalestorm;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.kevin.unisalestoem.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Login extends AppCompatActivity {

    public static String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Parse.initialize(this, "uWclLZByL5x9wsKvdTvJyZhkL7GFlEWbZz07t3aQ", "0J08mMNuUXaKpP04wMyCS6RphTFzjfwbNzjIPXb5");
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    //This puts the view when the done button is pressed
    //Takes you to the Google Maps page

    public void logincheck(View view) {
     //   EditText uName   = (EditText)findViewById(R.id.username);
        username = ((EditText)findViewById(R.id.username)).getText().toString();
        ParseObject parseUsernames = new ParseObject("Usernames");



/*
        // Locate the image in res > drawable-hdpi
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.greenplus);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("greenplus.png", image);
        // Upload the image into Parse Cloud
        file.saveInBackground();

        // Create a New Class called "ImageUpload" in Parse
        ParseObject parseMeal = new ParseObject("Meal");
        parseMeal.put("title","title" );
        parseMeal.put("author","auth" );
        parseMeal.put("rating","5" );


        // Create a column named "ImageFile" and insert the image
        parseMeal.put("photo", file);

        // Create the class and the columns
        parseMeal.saveInBackground();

        */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Usernames");
        query.whereEqualTo("email", username);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> arg0, ParseException arg1) {
                EditText parsePassword = (EditText) findViewById(R.id.password);
                String password = parsePassword.getText().toString();
                ParseObject user_pass = arg0.get(0);
                Context c = Login.this;
                String password_user = user_pass.getString("passwords");
                if (password.equals(password_user)) {
                    Intent intent = new Intent(Login.this, mainPage.class);
                    startActivity(intent);
                } else {
                 //   Toast.makeText(c, "Improper Login", Toast.LENGTH_LONG).show();
                    parsePassword.setError("Wrong password");
                }
            }
        });
    }
}
