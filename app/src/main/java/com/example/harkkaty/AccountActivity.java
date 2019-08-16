package com.example.harkkaty;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
    public FrameLayout makeAccountView, allEvents;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycAdapter;
    private RecyclerView.LayoutManager recycManager;
    private ArrayList<AccountEvents> events;
    private ArrayList<String> stringList;
    private StringUtility StringU;

    private String proxy;

    //position used when moving from activities to where the spinner position is important
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        manager=getSupportFragmentManager();
        listU = ListUtility.getListUtility();
        user = user.getCurrentUser();
        accounts = findViewById(R.id.Accounts);
        makeAccountView = findViewById(R.id.newAccount);
        makeAccountView.setBackgroundColor(getResources().getColor(R.color.white));
        makeAccountView.setVisibility(View.INVISIBLE);
        allEvents = findViewById(R.id.AllEvents);
        allEvents.setBackgroundColor(getResources().getColor(R.color.white));
        allEvents.setVisibility(View.INVISIBLE);
        StringU = StringUtility.getInstance();
        recyclerView = findViewById(R.id.AccountEvents);
        recycManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycManager);
        makeSpinner();
        setViewEvents();
    }


    //puts listener for the spinner
    private void makeSpinner(){
        String[] str = new String[user.getAccountAmount()];
        //the proxy is used to find account selected

        str= listU.MakeAccountList(user.getAccountAmount());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accounts.setAdapter(adapter);
        accounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                proxy= accounts.getSelectedItem().toString();
                account=user.getSelectedAccount(proxy);
                setViewEvents();
                position = accounts.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //a method which trigger when spinner item is changed and open the old events
    private void setViewEvents(){
        if (account ==null){
            return;
        }
        events= account.setEvents();
        if(events==null){
            return;
        }
        String[] str = StringU.getFourEvents(events);
        recycAdapter = new myAdapter(str, this);
        ((myAdapter) recycAdapter).setType(1);
        recyclerView.setAdapter(recycAdapter);

    }


    //moves to all events fragment, where all past events are shown
    public void showAllEvents(View v){
        allEvents = findViewById(R.id.AllEvents);
        allEvents.setVisibility(View.VISIBLE);
        fragment = new AllEvents_fragment();
        allEvents.bringToFront();
        FragmentTransaction transaction = manager.beginTransaction();
        //next sends the name of the account to the fragment so it can be used to get all the events
        Bundle bundle = new Bundle();
        bundle.putString(proxy, "accountInfo");
        fragment.setArguments(bundle);
        Intent intent = new Intent();
        transaction.replace(allEvents.getId(), fragment);
        transaction.commit();
    }

    public void leaveEvents(){
        allEvents.setVisibility(View.INVISIBLE);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(fragment);
        transaction.commit();
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
            makeAccountView = findViewById(R.id.newAccount);
            makeAccountView.setVisibility(View.VISIBLE);
            fragment = new makeAccount();
            makeAccountView.bringToFront();
            FragmentTransaction transaction = manager.beginTransaction();
            Intent intent = new Intent();
            System.out.println("BEfore manage");
            transaction.replace(makeAccountView.getId(), fragment);
            transaction.commit();
        }else{
            System.out.println("ELSE");
            makeAccountView.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
            makeSpinner();
        }
    }

    //goes to bankCard activity but with current account shown first
    public void goToBankCards(View v){
        if(account==null){
            //todo toast here
            return;
        }
        Intent newIntent= new Intent(AccountActivity.this, userCards.class);
        newIntent.putExtra("spinnerPosition", position);
        this.finish();
        AccountActivity.this.startActivity(newIntent);
    }

    //Home button which reStart home activity
    public void goHome(View v){
        Intent newIntent= new Intent(AccountActivity.this, Home.class);
        this.finish();
        AccountActivity.this.startActivity(newIntent);

    }



}
