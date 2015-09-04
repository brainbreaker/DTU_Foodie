package com.brainbreaker.dtufoodie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
//import android.database.SQLException;
import java.sql.SQLException;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerHeaderItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

public class Home extends DrawerActivity {
    Context context= this;
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private CardRecyclerView mRecyclerView;
    final int TOTAL_CARDS = 4;
    String category;
    String weekday;
    Integer fimage;
    String food;
    String place[] = {"VVS Hostel Mess","HJB Hostel Mess","CVR Hostel Mess","Aryabhatt Hostel Mess"};
    String pricebreakfast[]= {"Rs. 25 Per Plate","Rs. 25 Per Plate","Rs. 25 Per Plate","Rs. 25 Per Plate"};
    String pricelunch[]= {"Rs. 50 Per Plate","Rs. 50 Per Plate","Rs. 50 Per Plate","Rs. 50 Per Plate"};
    String pricesnacks[]= {"Rs. 15 Per Plate","Rs. 15 Per Plate","Rs. 15 Per Plate","Rs. 15 Per Plate"};
    String pricedinner[]= {"Rs. 50 Per Plate","Rs. 50 Per Plate","Rs. 50 Per Plate","Rs. 50 Per Plate"};
    String price[];
    String dayofweek;
    Integer time;
    Button insert;
    private Toolbar toolbar;
    private DrawerView drawer;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerView) findViewById(R.id.drawer);
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
//        drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.appeti_dull_yellow));
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.closeDrawer(drawer);

        drawer.addItem(new DrawerItem()
                        .setRoundedImage((BitmapDrawable) getResources().getDrawable(R.drawable.ghewar))
                        .setTextPrimary(getString(R.string.title_section1))
        );
        drawer.addItem(new DrawerItem()
                        .setRoundedImage((BitmapDrawable) getResources().getDrawable(R.drawable.bikaneripapad))
                        .setTextPrimary(getString(R.string.title_section2))
        );
        addDivider();


        drawer.addItem(new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.taj))
                        .setTextPrimary(getString(R.string.title_section3))
        );
        addDivider();

        drawer.addItem(new DrawerItem()
                        .setRoundedImage((BitmapDrawable) getResources().getDrawable(R.drawable.jalmahal), DrawerItem.AVATAR)
                        .setTextPrimary(getString(R.string.title_section4))
        );
        addDivider();

        drawer.addFixedItem(new DrawerItem()
                        .setRoundedImage((BitmapDrawable) getResources().getDrawable(R.drawable.taj), DrawerItem.AVATAR)
                        .setTextPrimary(getString(R.string.title_fixed_section5))
        );
        addDivider();

        drawer.addFixedItem(new DrawerItem()
                        .setRoundedImage((BitmapDrawable) getResources().getDrawable(R.drawable.jalmahal), DrawerItem.AVATAR)
                        .setTextPrimary(getString(R.string.title_fixed_section6))
        );

        drawer.setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                drawer.selectItem(position);
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        break;
                    case 1:
//                        intent = new Intent(getApplicationContext(), Home.class);
//                        startActivity(intent);
                        Toast.makeText(Home.this, " This Feature Is Not Available Yet." + position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                        intent = new Intent(getApplicationContext(), flavours.class);
//                        startActivity(intent);
                        Toast.makeText(Home.this, "This Feature Is Not Available Yet." + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        drawer.setOnFixedItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                drawer.selectFixedItem(position);
                Toast.makeText(Home.this, "Clicked fixed item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        drawer.addProfile(
                new DrawerProfile()
                        .setId(1)
                        .setRoundedAvatar((BitmapDrawable) getResources().getDrawable(R.drawable.taj))
                        .setBackground(getResources().getDrawable(R.drawable.rannofkutch))
                        .setName(getString(R.string.app_name))
                        .setDescription(getString(R.string.welcome))
        );

//        String week[] = {"Monday","Monday","Monday","Monday","Monday","Monday","Monday"};
//        for (int i = 0; i<5; i++) {
//            switch (week[i]) {
//                case "Monday":
//                    Toast.makeText(Home.this, "SWITCH OF MONDAY", Toast.LENGTH_LONG).show();
//                    break;
//            }
//        }
        ArrayList<Card> cards = new ArrayList<>();
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(this, cards);
        CardRecyclerView mRecyclerView = (CardRecyclerView) findViewById(R.id.card_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the empty view
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
        //Load cards
        new LoaderAsyncTask().execute();
    }
    public String getWeekday()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date date = new Date();
        dayofweek = sdf.format(date);
        return dayofweek;
    }

    public Integer getHourTime(){

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("HH");
        time = Integer.parseInt(df.format(c.getTime()));
        return time;
    }

    public String Category(){

        Integer hour = getHourTime();
        if(0<=hour && hour<=11){
            category = "Breakfast";
        }
        else if(11<=hour && hour<=14){
            category = "Lunch";
        }
        else if(14<hour && hour<18){
            category = "Snacks";
        }
        else if(18<=hour && hour<=24){
            category = "Dinner";
        }

        return category;
    }

    public String[] price(){
        String category = Category();

        if (category.equals("Breakfast"))
        {
           price = pricebreakfast;
        }
        else if (category.equals("Snacks"))
        {
            price = pricesnacks;
        }
        else if (category.equals("Lunch"))
        {
            price = pricelunch;
        }
        else if (category.equals("Dinner"))
        {
            price = pricedinner;
        }
        return price;
    }

    private ArrayList<Card> initCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < TOTAL_CARDS; i++) {
            final ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();
            weekday= getWeekday();
            category = Category();
            price = price();
            if(weekday.equals("Monday")) {

                if (category.equals("Breakfast")) {

                        switch (i) {
                            case 0:
                                food = "Masala Dosa";
                                fimage = R.drawable.ghewar;
                                break;
                            case 1:
                                food = "Upma, Sambhar Vada";
                                fimage = R.drawable.marinedrive;
                                break;
                            case 2:
                                food = "XXX";
                                fimage = R.drawable.taj;
                                break;
                            case 3:
                                food = "Upma, Sambhar Vada";
                                fimage = R.drawable.tajmahal;
                                break;
                        }
                }

                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "XXX";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Snacks")) {

                    switch (i) {
                                case 0:
                                    food = "Samosa";
                                    fimage = R.drawable.ghewar;
                                    break;
                                case 1:
                                    food = "Samosa";
                                    fimage = R.drawable.marinedrive;
                                    break;
                                case 2:
                                    food = "Samosa";
                                    fimage = R.drawable.taj;
                                    break;
                                case 3:
                                    food = "Samosa";
                                    fimage = R.drawable.tajmahal;
                                    break;
                    }
                }

                if (category.equals("Dinner")) {
                    switch (i) {
                        case 0:
                            food = "Kofta/Egg Curry, Sewai Kheer";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Kofta, Sewai Kheer";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Kofta/Egg Curry, Sewai Kheer";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
            }


            else if (weekday.equals("Tuesday")) {
                if (category.equals("Breakfast")) {

                    switch (i) {
                        case 0:
                            food = "Aalo Parantha";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Plain Parantha, Aalo sabzi";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Aalo Parantha";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "XXX";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "xxx";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "Poha";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Poha";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "Poha";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "XXX";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Dinner")) {
                    switch (i) {
                        case 0:
                            food = "Paalak Paneer, Custard, Chhole, Puri";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Shahi Paneer, Custard, Chhole, Puri";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "Shahi Paneer, Custard, Chhole, Puri";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Shahi Paneer, Custard, Chhole, Puri";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
            }


            if (weekday.equals("Wednesday")) {
                if (category.equals("Breakfast")) {

                    switch (i) {
                        case 0:
                            food = "Chhole Bhature";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "Plain Parantha, Aalo sabzi";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Pav Bhaji/Chhole Kulche/Plain Parantha, Aalo Sabzi";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "Chowmein";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "Ghiya(Bottleguard), Boondi Rayta, Chapatis";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "xxx";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "Pastry, Coffee";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Bread Sandwich";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "Pastry, Coffee";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Pastry, Coffee";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Dinner")) {
                    switch (i) {
                        case 0:
                            food = "Chicken,xxx";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Matar Paneer, Halwa, XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Matar Paneer/Chicken, Halwa";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
            }
            if (weekday.equals("Thursday")){
                   if (category.equals("Breakfast")) {


                       switch (i) {
                           case 0:
                               food = "Masala Dosa";
                               fimage = R.drawable.ghewar;
                               break;
                           case 1:
                               food = "Upma, Sambhar Vada";
                               fimage = R.drawable.marinedrive;
                               break;
                           case 2:
                               food = "XXX";
                               fimage = R.drawable.taj;
                               break;
                           case 3:
                               food = "Chhole Bhature";
                               fimage = R.drawable.tajmahal;
                               break;
                       }
                   }
                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "Masala Dosa";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Upma, Sambhar Vada";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "Masala Dosa";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Upma, Sambhar Vada";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Dinner")) {
                    switch (i) {
                        case 0:
                            food = "xxx";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "xxx";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
            }
            if (weekday.equals("Friday"))
            {
                if (category.equals("Breakfast")) {

                    switch (i) {
                        case 0:
                            food = "Pav Bhaji";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Masala Dosa";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "Aaloo Sabzi, Boondi Rayta, Dal ";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Mix Veg, Boondi Rayta, Lobiya Dal(Black Eyed Bean)";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "MESS OPEN(Paid Mess)";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "MESS OFF";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "MESS OFF";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "MESS OFF";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Dinner")){
                            switch (i) {
                                case 0:
                                    food = "(PAID MESS)Fried Rice, Egg Fried Rice, Masala Dosa, Aalo Parantha, Uttpam, Omlette";
                                    fimage = R.drawable.ghewar;
                                    break;
                                case 1:
                                    food = "MESS OFF";
                                    fimage = R.drawable.marinedrive;
                                    break;
                                case 2:
                                    food = "MESS OFF";
                                    fimage = R.drawable.taj;
                                    break;
                                case 3:
                                    food = "MESS OFF";
                                    fimage = R.drawable.tajmahal;
                                    break;
                            }
                }
            }
            if (weekday.equals("Saturday")) {
                if (category.equals("Breakfast")) {

                    switch (i) {
                        case 0:
                            food = "Puri-Sabzi";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Puri Sabzi";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "XXX";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Khichdi";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Khichdi";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "XXX";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Dinner")){
                            switch (i) {
                                case 0:
                                    food = "Masala Dosa";
                                    fimage = R.drawable.ghewar;
                                    break;
                                case 1:
                                    food = "Upma, Sambhar Vada";
                                    fimage = R.drawable.marinedrive;
                                    break;
                                case 2:
                                    food = "XXX";
                                    fimage = R.drawable.taj;
                                    break;
                                case 3:
                                    food = "YYY";
                                    fimage = R.drawable.tajmahal;
                                    break;
                            }
                      }
                }
            if (weekday.equals("Sunday")) {
                if (category.equals("Breakfast")) {

                    switch (i) {
                        case 0:
                            food = "Aalo Parantha";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Aalo Parantha";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }

                if (category.equals("Lunch")) {

                    switch (i) {
                        case 0:
                            food = "Shahi Paneer, Veg Rayta, Pulao, Lobiya Dal(XXX)";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Shahi Paneer, Veg Rayta, Pulao, Lobiya Dal(XXX)";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Shahi Paneer, Veg Rayta, Pulao, Lobiya Dal(XXX)";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Snacks")) {

                    switch (i) {
                        case 0:
                            food = "Masala Dosa";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "Upma, Sambhar Vada";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "Bread Sandwich";
                            fimage = R.drawable.tajmahal;
                            break;
                    }
                }
                if (category.equals("Dinner")) {
                    switch (i) {
                        case 0:
                            food = "XXX";
                            fimage = R.drawable.ghewar;
                            break;
                        case 1:
                            food = "XXX";
                            fimage = R.drawable.marinedrive;
                            break;
                        case 2:
                            food = "XXX";
                            fimage = R.drawable.taj;
                            break;
                        case 3:
                            food = "YYY";
                            fimage = R.drawable.tajmahal;
                            break;
                    }

                    }
            }
            // Set supplemental actions
            TextSupplementalAction t1 = new TextSupplementalAction(this, R.id.action1);
            t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
                Intent intent;
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(Home.this, "WORKING!", Toast.LENGTH_LONG).show();
                }
            });
            actions.add(t1);

            TextSupplementalAction t2 = new TextSupplementalAction(this, R.id.action2);
            t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {

                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(Home.this, "WORKING! for t2", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Home.this, Choose.class);
                    Home.this.startActivity(intent);

                }
            });
            actions.add(t1);
            actions.add(t2);


//            Create a Card, set the title over the image and set the thumbnail
              MaterialLargeImageCard card =
                    MaterialLargeImageCard.with(this)
                            .setTextOverImage(food)
                            .setTitle(place[i])
                            .setSubTitle(price[i])
                            .useDrawableId(fimage)
                            .setupSupplementalActions(R.layout.products_supplimental_actions_layout, actions)
                            .build();

            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(Home.this," Click on ActionArea ",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Home.this, InsertData.class);
                            Home.this.startActivity(intent);
                }
            });
            card.build();
            cards.add(card);
        }
        return cards;
    }
    private void updateAdapter(ArrayList<Card> cards) {
        if (cards != null) {
            mCardArrayAdapter.addAll(cards);
        }
    }

    class LoaderAsyncTask extends AsyncTask<Void, Void, ArrayList<Card>> {

        LoaderAsyncTask() {
        }

        @Override
        protected ArrayList<Card> doInBackground(Void... params) {
            //elaborate images
            //SystemClock.sleep(1000); //delay to simulate download, don't use it in a real app

            ArrayList<Card> cards = initCard();
            return cards;

        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            //Update the adapter
            updateAdapter(cards);
            //displayList();
        }

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
