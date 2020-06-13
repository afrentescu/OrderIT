package com.example.orderit;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DinerActivity extends AppCompatActivity {
  EditText etSearch;
    int[]  IMAGES ={ R.drawable.diner2_noeme, R.drawable.diner3_haute, R.drawable.diner4_pio, R.drawable.diner_5_cucinetta, R.drawable.diner_6_kane };
    String[]  NAMES = {"NOEME", "HAUTE", "PIO", "CUCINETTA", "KANE"};
    RecyclerView recycleViesDiner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diner_layout);
        recycleViesDiner = findViewById(R.id.rvDinerRestaurants);
     etSearch = findViewById(R.id.etSearchDiner);


        DinerAdapter customAdapter = new DinerAdapter(this, NAMES, IMAGES);
        recycleViesDiner.setAdapter(customAdapter);
        recycleViesDiner.setLayoutManager(new LinearLayoutManager(this));

    }


}
