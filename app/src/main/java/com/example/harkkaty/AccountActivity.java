package com.example.harkkaty;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private Spinner accounts;
    private Spinner currentAccount;
    private User user;
    private ListUtility listU;
    private Account account;
    private FragmentManager manager;
    private Fragment fragment;
    private FrameLayout makeAccountView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycAdapter;
    private RecyclerView.LayoutManager recycManager;
    private ArrayList events;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        listU = listU.getListUtility();
        user = user.getCurrentUser();
        accounts = findViewById(R.id.Accounts);
        makeAccountView = findViewById(R.id.newAccount);
        makeSpinner();
    }


    private void makeSpinner(){
        String[] str = new String[user.getAccountAmount()];
        //the proxy is used to find account selected

        str= listU.MakeAccountList(user.getAccountAmount());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, str);
        accounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String proxy;
                proxy= accounts.getSelectedItem().toString();
                account=user.getSelectedAccount(proxy);
                setViewEvents();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //a method which trigger when spinner item is changed and open the old events
    private void setViewEvents(){
        events= account.setEvents();//TOdo make this work

    }


    //moves to all events fragment, where all past events are shown
    public void showAllEvents(View v){

    }

    //goes to activity where new payments can be made, money can lifted and money added
    public void goToAccountNewEvents(View v){
        Intent newIntent= new Intent(AccountActivity.this, AccountNewActivity.class);
        this.finish();
        AccountActivity.this.startActivity(newIntent);
    }

    // goes to add account fragment; there new accounts can be made
    public void goToAddAccount(View v){
        if(manager.getFragments().isEmpty()){
            makeAccountView.setVisibility(View.VISIBLE);
            fragment = new makeAccount();
            makeAccountView.bringToFront();
            FragmentTransaction transaction = manager.beginTransaction();
            Intent intent = new Intent();
            transaction.replace(R.id.makeTheAccount, fragment);
            transaction.commit();
        }else{
            makeAccountView.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
        }
    }

    //goes to bankCard activity but with current account shown first
    public void goToBankCards(View v){

    }

    //Home button which reStart home activity
    public void goHome(View v){

    }
}
