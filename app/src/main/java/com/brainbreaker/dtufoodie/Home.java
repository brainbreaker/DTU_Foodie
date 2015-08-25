package com.brainbreaker.dtufoodie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import com.brainbreaker.dtufoodie.database.FoodieDatabase;
import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerHeaderItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

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
    String food;
    String place[] = {"VVS Hostel Mess","HJB Hostel Mess","CVR Hostel Mess","Aryabhatt Hostel Mess"};
    String price[] =  {"Rs. 25 Per Plate","Rs. 25 Per Plate","Rs. 25 Per Plate","Rs. 25 Per Plate"};
    String title;
    TextView weekday;
    Button insert;
    int pimage[]={R.drawable.marinedrive,R.drawable.taj,R.drawable.tajmahal,R.drawable.bikaneripapad,R.drawable.ghewar,R.drawable.jalmahal};

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



        weekday= (TextView) findViewById(R.id.weekday);
        TextView weekday2 = (TextView) findViewById(R.id.weekday2);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayofweek = sdf.format(date);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());

// Database Operations
        FoodieDatabase fdb = new FoodieDatabase(this);
        Cursor CR = fdb.retrievevalues(fdb);
        CR.moveToFirst();
        do {
            String hostelname = CR.getString(1);
            String weekday = CR.getString(2);
            String category = CR.getString(3);
            String food = CR.getString(4);
            String rate = CR.getString(5);
            Toast.makeText(Home.this, hostelname, Toast.LENGTH_LONG).show();
            Toast.makeText(Home.this, weekday, Toast.LENGTH_LONG).show();
        }
        while(CR.moveToNext());
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

    private ArrayList<Card> initCard() {




        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < TOTAL_CARDS; i++) {
            final ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();


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
                    Intent intent = new Intent(Home.this, Details.class);
                    Home.this.startActivity(intent);

                }
            });
            actions.add(t1);
            actions.add(t2);
            switch (i){
                case 0: food = "Aalo Parantha";
                    break;
                case 1: food = "Chhole Bhature";
                    break;
                case 2: food = "Pav Bhaji";
                    break;
                case 3: food = "Masala Dosa";
                    break;

            }
//            Create a Card, set the title over the image and set the thumbnail
              MaterialLargeImageCard card =
                    MaterialLargeImageCard.with(this)
                            .setTextOverImage(food)
                            .setTitle(place[i])
                            .setSubTitle(price[i])
                            .useDrawableId(pimage[i])
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