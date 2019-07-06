package com.example.harkkaty;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // Edit text that are used to see what user wrote to username and password textboxes respectively
    protected EditText userN;
    protected EditText passW;
    public FrameLayout newUse;
    private FragmentManager manager;
    protected Fragment fragment;
    private addnfoView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userN = (EditText) findViewById(R.id.userName);
        passW = (EditText) findViewById(R.id.password);

        manager= getSupportFragmentManager();
        newUse = findViewById(R.id.newUser);
        newUse.bringToFront();
        newUse.setVisibility(View.INVISIBLE);

        newUse.setBackgroundColor(getResources().getColor(R.color.white));
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
        //newUse.setLayoutParams(new ConstraintLayout.LayoutParams(400,700));
        newUse.setVisibility(View.VISIBLE);
        fragment = new addnfoView();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.newUser, fragment);
        transaction.commit();

    }

    public void cancleInfo(){

        newUse.setVisibility(View.INVISIBLE);
    }

  /* @Override
   public void cancleInfo(){
       newUse.setVisibility(View.INVISIBLE);
   }*/

    //todo last thing make maseter näkymä
    protected void swtichToMasteView(){

    }

    //todo make switch activity
    protected void switchToMainUserView(){

    }
}
