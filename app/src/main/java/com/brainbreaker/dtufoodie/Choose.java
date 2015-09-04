package com.brainbreaker.dtufoodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import fr.ganfra.materialspinner.MaterialSpinner;

public class Choose extends AppCompatActivity {
    String[] weekday;
    String[] places;
    MaterialSpinner weekspinner;
    MaterialSpinner placespinner;
    info.hoang8f.widget.FButton breakfast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        placespinner = (MaterialSpinner) findViewById(R.id.placesspinner);
        places = getResources().getStringArray(R.array.places_array);
        ArrayAdapter<String> placeadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, places);
        placeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placespinner.setAdapter(placeadapter);

        weekspinner = (MaterialSpinner) findViewById(R.id.weekspinner);
        weekday = getResources().getStringArray(R.array.week_array);
        ArrayAdapter<String> weekadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weekday);
        weekadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekspinner.setAdapter(weekadapter);

        breakfast =  (info.hoang8f.widget.FButton) findViewById(R.id.BREAKFAST);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, Details.class);
                Choose.this.startActivity(intent);
            }
        });

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
}
