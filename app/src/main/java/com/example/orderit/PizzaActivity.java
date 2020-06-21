package com.example.orderit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class PizzaActivity extends AppCompatActivity {
    ColorDrawable colorDrawable;
    ActionBar bar;
    private DrawerLayout dl ;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    RecyclerView rvList;
    String[] Restaurants = { "Trattoria", "Volare", "Paine si vin", "Pizza Mania"};
    int[] IMAGES = { R.drawable.trattoria, R.drawable.volare,R.drawable.painesivin,  R.drawable.pizzamania };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diner_layout);

        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);
        rvList = findViewById(R.id.rvDinerRestaurants);

        dl = (DrawerLayout)findViewById(R.id.diner_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_no_smoke){
                    Toast.makeText(PizzaActivity.this, "No_smoke",Toast.LENGTH_SHORT).show();
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
        DinerAdapter customAdapter = new DinerAdapter(this, Restaurants, IMAGES);
        rvList.setAdapter(customAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    }

