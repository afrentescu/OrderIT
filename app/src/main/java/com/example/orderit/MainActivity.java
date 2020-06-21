package com.example.orderit;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomAdapter.OnRestaurantListener {
    ColorDrawable colorDrawable;
    ActionBar bar;
    private DrawerLayout dl ;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    RecyclerView  recycleViesCategories;

    int[] IMAGES = { R.drawable.diner, R.drawable.steakhouse,R.drawable.sushi,  R.drawable.pizza , R.drawable.caffes};
    String[] CATEGORIES = {"Diner", "Steak House", "Sushi", "Pizza", "Coffees"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);


        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        recycleViesCategories = findViewById(R.id.recycleViewCategories);
  CustomAdapter customAdapter = new CustomAdapter(this, CATEGORIES, IMAGES,  this);
  recycleViesCategories.setAdapter(customAdapter);
  recycleViesCategories.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                    if(id == R.id.nav_no_smoke){
                        Toast.makeText(MainActivity.this, "No_smoke",Toast.LENGTH_SHORT).show();
                    }
                    else
                   if(id == R.id.nav_restaurant)
                   {
                       Intent intent4 = new Intent(getApplicationContext(), RestaurantsActivity.class);
                       startActivity(intent4);
                   }
                   else
                   if(id == R.id.nav_tutorial){
                       Intent intent3 = new Intent(getApplicationContext(), VideoTutorial.class);
                       startActivity(intent3);
                   }
                   else
                   if(id == R.id.nav_facebook)
                   {
                       Intent intent2 = new Intent(getApplicationContext(), SocialLoginActivity.class);
                       startActivity(intent2);

                   }
                   else
                      if(id == R.id.nav_share){
                          Intent intent = new Intent(Intent.ACTION_SEND);
                          intent.setType("application/vnd.android.package-archive");
                          //    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkp
                          startActivity(Intent.createChooser(intent, "shareVia"));
                      }


                return true;

            }
        });

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRestaurantClick(int position) {
        if(position ==0){
            Intent intent = new Intent ( getApplicationContext(), DinerActivity.class);
            startActivity(intent);
        }
        if(position == 1){
            Intent intent1 = new Intent(getApplicationContext(), SteakHouseActivity.class);
            startActivity(intent1);
        }
        if(position == 2){
            Intent intent1 = new Intent(getApplicationContext(), SushiActivity.class);
            startActivity(intent1);
        }
        if(position == 3){
            Intent intent1 = new Intent(getApplicationContext(), PizzaActivity.class);
            startActivity(intent1);
        }
        if(position == 4){
            Intent intent1 = new Intent(getApplicationContext(), CoffeesActivity.class);
            startActivity(intent1);
        }
    }

}