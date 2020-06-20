package com.example.orderit;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DinerActivity extends AppCompatActivity implements View.OnClickListener {
  TextView tvTable;
    int[]  IMAGES ={ R.drawable.diner2_noeme, R.drawable.diner3_haute, R.drawable.diner4_pio, R.drawable.diner_5_cucinetta, R.drawable.diner_6_kane };
    String[]  NAMES = {"NOEME", "HAUTE", "PIO", "CUCINETTA", "KANE"};
    RecyclerView recycleViesDiner;
    ColorDrawable colorDrawable;
    ActionBar bar;
    String tableNumber;
    private DrawerLayout dl ;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diner_layout);
        recycleViesDiner = findViewById(R.id.rvDinerRestaurants);
            tvTable = findViewById(R.id.tvTableBNumber);

        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);
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
                switch(id)
                {
                    case R.id.nav_no_smoke:
                        Toast.makeText(DinerActivity.this, "Smoke Free PLaces",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_restaurant:
                        Toast.makeText(DinerActivity.this, "Restaurants",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_facebook: {
                        Intent intent2 = new Intent(getApplicationContext(), SocialLoginActivity.class);
                        startActivity(intent2);

                    }
                    case R.id.nav_share:
                    {
                        //  ApplicationInfo api = getApplicationContext().getApplicationInfo();
                        //  String apkpath = api.sourceDir;
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("application/vnd.android.package-archive");
                        //    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                        startActivity(Intent.createChooser(intent, "shareVia"));
                    }
                    default:
                        return true;
                }


                return true;

            }
        });
        DinerAdapter customAdapter = new DinerAdapter(this, NAMES, IMAGES);
        recycleViesDiner.setAdapter(customAdapter);
        recycleViesDiner.setLayoutManager(new LinearLayoutManager(this));
      /*  tableNumber = (String) tvTable.getText();
        Intent intent = new Intent(getApplicationContext(), Scanner.class);
        intent.putExtra("TABLENR", tableNumber);*/
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
