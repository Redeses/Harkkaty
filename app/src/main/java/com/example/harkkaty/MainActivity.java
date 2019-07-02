package com.example.harkkaty;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // Edit text that are used to see what user wrote to username and password textboxes respectively
    protected EditText userN;
    protected EditText passW;
    protected FrameLayout newUse;

    protected Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userN = (EditText) findViewById(R.id.userName);
        passW = (EditText) findViewById(R.id.password);

    }



    public void logIn(View v){
        String username = String.valueOf(userN.getText());
        String password= String.valueOf(passW.getText());
        /*boolean isUser;
        boolean isAdmin;
        isAdmin = checkIfAdmin();
        if(isAdmin == true){
            swtichToMasteView();
        }
        isUser = checkIfUser(username,password);
        if(isUser==true){
            switchToMainUserView();
        }
        */
    }

    //
    public void makeUser(View v){
        newUse = findViewById(R.id.newUser);
        //newUse.setLayoutParams(new ConstraintLayout.LayoutParams(400,700));
        newUse.setBackgroundColor(getResources().getColor(R.color.white));
        newUse.bringToFront();
        fragment = new addnfoView();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.newUser, fragment);
        transaction.commit();
    }

    protected void swtichToMasteView(){

    }

    protected void switchToMainUserView(){

    }
}
