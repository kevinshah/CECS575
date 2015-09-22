package com.example.kevin.unisalestoem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;


import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseException;

public class Login extends AppCompatActivity {

    public static String name = "";
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

    public void createAccount(View view)
    {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    //This puts the view when the done button is pressed
    //Takes you to the Google Maps page

    public void logincheck(View view)
    {
        EditText username   = (EditText)findViewById(R.id.username);
        String u = username.getText().toString();
        name = u;


        ParseObject gameList = new ParseObject("Usernames");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Usernames");
        query.whereEqualTo("firstname", u);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> arg0, ParseException arg1) {
                EditText password = (EditText) findViewById(R.id.password);
                String p = password.getText().toString();
                ParseObject user_pass = arg0.get(0);
                Context c = Login.this;
                String password_user = user_pass.getString("passwords");
                if (p.equals(password_user)) {
                    Intent intent = new Intent(Login.this, mainPage.class);
                    startActivity(intent);
                } else
                    Toast.makeText(c, "Improper Login" , Toast.LENGTH_LONG).show();
            }
        });
    }
}
