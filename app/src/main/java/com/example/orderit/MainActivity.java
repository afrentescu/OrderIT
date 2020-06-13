package com.example.orderit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ColorDrawable colorDrawable;
    ActionBar bar;
    private DrawerLayout dl ;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    RecyclerView  recycleViesCategories;

    int[] IMAGES = { R.drawable.diner, R.drawable.steakhouse,R.drawable.sushi, R.drawable.teahouse,   R.drawable.pizza ,R.drawable.dinnerinthesky, R.drawable.caffes, R.drawable.fastfood, R.drawable.foodtruck};
    String[] CATEGORIES = {"Diner", "Steak House", "Sushi", "Teahouse","Pizza","Dinner in the Sky", "Coffees", "Fast Food", "Food truck"};
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
  CustomAdapter customAdapter = new CustomAdapter(this, CATEGORIES, IMAGES);
  recycleViesCategories.setAdapter(customAdapter);
  recycleViesCategories.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_no_smoke:
                        Toast.makeText(MainActivity.this, "No_smoke",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_restaurant:
                        Toast.makeText(MainActivity.this, "Restaurants",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this, "Share",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }


}