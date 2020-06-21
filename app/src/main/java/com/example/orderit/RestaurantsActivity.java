package com.example.orderit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
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

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {
 ArrayList<String> myArray;
    ColorDrawable colorDrawable;
    ActionBar bar;
    private DrawerLayout dl ;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    RecyclerView rvList;
    String[] Restaurants = {"OSHO", "Vacamuu", "Red Angus", "Vibes 19", "Argentine", "Ginger",  "Wasabi running", "Zen Sushi", "Kanpai", "Trattoria", "Volare", "Paine si vin", "Pizza Mania", "Origo", "Ganesha", "Van Gogh", "Cereal Crunch" };
    int[] IMAGES = { R.drawable.osho, R.drawable.vacamuuu,R.drawable.redangus,  R.drawable.vibes19 , R.drawable.argentine, R.drawable.ginger, R.drawable.wasabi, R.drawable.zen, R.drawable.kanpai, R.drawable.trattoria, R.drawable.volare, R.drawable.painesivin, R.drawable.pizzamania, R.drawable.origo, R.drawable.ganesha, R.drawable.vang, R.drawable.cereal};
 EditText etSearch;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_restaurants_list_layout);

        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);
        rvList = findViewById(R.id.rvRestaurantsList);

        dl = (DrawerLayout)findViewById(R.id.all_restaurants_list_layout);
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
                    Toast.makeText(RestaurantsActivity.this, "No_smoke",Toast.LENGTH_SHORT).show();
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


        etSearch = findViewById(R.id.etSearchRestaurant);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
           //   filter(s.toString());
            }
        });
      //  buildRecycleView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
