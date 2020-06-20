package com.example.orderit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.constants.ListAppsActivityContract;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
int MY_PERMISSION_REQUEST_CAMERA =0;
TextView tvTable;
    ZXingScannerView scannerView;
    Intent intent =getIntent();
    String tableNr;
    ColorDrawable colorDrawable;
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
     //   tableNr = intent.getStringExtra("TABLENR");

    }


    @Override
    public void handleResult(Result result) {
        tvTable = findViewById(R.id.tvTableBNumber);
      //  tableNr = (String) tvTable.getText();
       Toast.makeText(this, "Your order was placed!" , Toast.LENGTH_LONG).show();

        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!=
        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_CAMERA);
        }
      scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}

