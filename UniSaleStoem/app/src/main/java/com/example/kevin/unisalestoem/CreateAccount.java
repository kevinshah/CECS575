package com.example.kevin.unisalestoem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

import com.parse.Parse;
import com.parse.ParseObject;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    Parse.enableLocalDatastore(this);

        Parse.initialize(this, "uWclLZByL5x9wsKvdTvJyZhkL7GFlEWbZz07t3aQ", "0J08mMNuUXaKpP04wMyCS6RphTFzjfwbNzjIPXb5");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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


    public void signUp(View view)
    {

        EditText username   = (EditText)findViewById(R.id.firstname);
        EditText password   = (EditText)findViewById(R.id.password);
        EditText repassword   = (EditText)findViewById(R.id.repassword);
        ParseObject gameScore = new ParseObject("Usernames");
        String p = password.getText().toString();
        String accountName = username.getText().toString();
        String re_p = repassword.getText().toString();
        final Context context = this;;
        if(p.equals(re_p))
        {
            gameScore.put("firstname",accountName );
            gameScore.put("passwords", p);
            gameScore.saveInBackground();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        else
            Toast.makeText(context , "Password didn't match",Toast.LENGTH_LONG).show();
    }

}
