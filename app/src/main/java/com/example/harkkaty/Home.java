package com.example.harkkaty;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

//home activity
public class Home extends AppCompatActivity {
    private String userID;
    Button profile, settings;
    private FrameLayout profileSettings;
    private Fragment fragment;
    private FragmentManager manager;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent getIntent = getIntent();
        //block where the userr who signed in is created to teh USer class
        userID = getIntent.getStringExtra("ID");
        user=user.getCurrentUser();
        user.setUserID(userID);
        user.addCurrentUser(userID);

        profileSettings = findViewById(R.id.profileSettings);
        profileSettings.bringToFront();
        profileSettings.setVisibility(View.INVISIBLE);
        settings=(Button) findViewById(R.id.setting);
        manager = getSupportFragmentManager();

    }



    //starts a new fragment that opens up infront of view and is used to change profile settings
    public void goProfile(View v){
        //newUse.setLayoutParams(new ConstraintLayout.LayoutParams(400,700));
        if(manager.getFragments().isEmpty()){
            profileSettings.setVisibility(View.VISIBLE);
            fragment = new profile();

            FragmentTransaction transaction = manager.beginTransaction();
            Intent intent = new Intent();
            intent.putExtra("ID", userID);
            transaction.replace(R.id.profileSettings, fragment);
            transaction.commit();
        }else{
            profileSettings.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
        }


    }


    //todo make functionality during onCreate to find all info connected to the user
}
