package com.brainbreaker.dtufoodie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brainbreaker.dtufoodie.database.FoodieDatabase;

public class InsertData extends AppCompatActivity {
    com.rengwuxian.materialedittext.MaterialEditText sno;
    com.rengwuxian.materialedittext.MaterialEditText hostel;
    com.rengwuxian.materialedittext.MaterialEditText category;
    com.rengwuxian.materialedittext.MaterialEditText food;
    com.rengwuxian.materialedittext.MaterialEditText weekday;
    com.rengwuxian.materialedittext.MaterialEditText rate;


    Button insert;

    String Shostel;
    String Scategory;
    String Sfood;
    String Sweekday;
    String Srate;

    String Isno;

    Context context= this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        sno = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.sno);
        hostel = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.hostel);
        category = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.category);
        food = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.food);
        weekday = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.weekday);
        rate = (com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.rate);

        insert = (Button) findViewById(R.id.insert);

        Isno = sno.getText().toString();
        Shostel = hostel.getText().toString();
        Scategory = category.getText().toString();
        Sfood = food.getText().toString();
        Sweekday = weekday.getText().toString();
        Srate = rate.getText().toString();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodieDatabase fdb = new FoodieDatabase(context);
                fdb.insertvalues(fdb,Isno,Shostel,Sweekday,Scategory,Sfood,Srate);
                Toast.makeText(InsertData.this,"Value No." + Isno + "inserted successfully.",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(InsertData.this, Home.class);
                InsertData.this.startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_data, menu);
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
