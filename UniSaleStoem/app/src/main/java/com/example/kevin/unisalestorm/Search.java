package com.example.kevin.unisalestorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.kevin.unisalestoem.R;

import java.util.List;

public class Search extends AppCompatActivity {


    String username;


    private List<ListHelper> listHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(Search.this, "You fucking just clicked on Search you dick Head Son of a Bitch.", Toast.LENGTH_LONG);
        username = getIntent().getExtras().getString("username");
        setContentView(R.layout.activity_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
    public void addItem(View view) {
        Intent intent = new Intent(this, addItem.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void search (View view){
        Toast.makeText(Search.this, "You fucking just clicked on Search you dick Head Son of a Bitch.", Toast.LENGTH_LONG);
    }

}
