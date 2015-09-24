package com.example.kevin.unisalestorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevin.unisalestoem.R;
import com.parse.ParseObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {
   private EditText firstname;
   private EditText lastname;
   private EditText email;
   private EditText password;
   private EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    Parse.enableLocalDatastore(this);

      //  Parse.initialize(this, "uWclLZByL5x9wsKvdTvJyZhkL7GFlEWbZz07t3aQ", "0J08mMNuUXaKpP04wMyCS6RphTFzjfwbNzjIPXb5");
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


    public void signUp(View view) {
        firstname   = (EditText)findViewById(R.id.firstname);
        lastname   = (EditText)findViewById(R.id.lastname);
        email   = (EditText)findViewById(R.id.email);
        password   = (EditText)findViewById(R.id.password);
        repassword   = (EditText)findViewById(R.id.repassword);

        ParseObject gameScore = new ParseObject("Usernames");
        String p = password.getText().toString();
        String saveFirstName = firstname.getText().toString();
        String saveLastName = lastname.getText().toString();
        String saveEmail = email.getText().toString();
        String re_p = repassword.getText().toString();
        final Context context = this;;

        final String EditEmail = email.getText().toString();

        if (!isValidEmail(EditEmail)) {
            email.setError("Invalid Email");
        }
        else if(p.equals(re_p)){
            gameScore.put("firstname",saveFirstName );
            gameScore.put("lastname",saveLastName );
            gameScore.put("email",saveEmail );
            gameScore.put("passwords", p);
            gameScore.saveInBackground();

            Intent intent = new Intent(this, mainPage.class);
            startActivity(intent);
        }
        else {
            password.setError("Password didn't match");
            repassword.setError("Password didn't match");
            // Toast.makeText(context , "Password didn't match",Toast.LENGTH_LONG).show();
        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "csulb.edu";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}
