package com.example.orderit;

import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomAdapter.OnRestaurantListener {
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
  CustomAdapter customAdapter = new CustomAdapter(this, CATEGORIES, IMAGES,  this);
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

        //Facebook Login
        setContentView(R.layout.login);
        loginButton = findViewById(R.id.login_button);
        txtName =  findViewById(R.id.profile_name);
        txtEmail =  findViewById(R.id.profile_email);
        circleImageView =  findViewById(R.id.profile_pic);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions("email", "public_profile");
        checkLoginStatus();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

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

    @Override
    public void onRestaurantClick(int position) {
        if(position ==0){
            Intent intent = new Intent ( getApplicationContext(), DinerActivity.class);
            startActivity(intent);
        }
    }

    //Facebook login stuff
    private LoginButton loginButton;
    private CircleImageView circleImageView;
    private TextView txtName, txtEmail;
    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null) {
                txtName.setText("");
                txtEmail.setText("");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this,"User Logged Out", Toast.LENGTH_LONG).show();
            }
            else
                loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");

                    String image_url = "http://graph.facebook.com/"+id+ "/picture?type=normal";

                    txtEmail.setText(email);
                    txtName.setText(first_name+" "+last_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken()!=null){
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }
}