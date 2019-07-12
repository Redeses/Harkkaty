package com.example.harkkaty;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class userCards extends AppCompatActivity {
    private User user;
    private ListUtility listU;
    private Spinner accounts;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycAdapter;
    private RecyclerView.LayoutManager recycManager;
    private Account account;
    private int position;
    private ArrayList<BankCard> bankCardList;
    private StringUtility StringU;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cards);
        user = user.getCurrentUser();
        listU= listU.getListUtility();
        accounts= findViewById(R.id.accountSpinner);
        position=getIntent().getIntExtra("spinnePosition", 0);
        StringU = StringUtility.getStringutility();

        makeSpinner();

    }


    private void makeSpinner(){
        String[] str = new String[user.getAccountAmount()];
        //the proxy is used to find account selected

        str= listU.MakeAccountList(user.getAccountAmount());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, str);
        accounts.setSelection(position);
        accounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String proxy;
                proxy= accounts.getSelectedItem().toString();
                account=user.getSelectedAccount(proxy);
                setViewCards();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // todo method which the recyclerView for cards
    private void setViewCards(){
        bankCardList= account.getCards();//TOdo make this work
        String[] str = StringU.getCards(bankCardList);
        recycAdapter = new myAdapter(str);
        type = 2;
        ((myAdapter) recycAdapter).setType(type);
        ((myAdapter) recycAdapter).setContext(this);
        recyclerView.setAdapter(recycAdapter);
    }

    // moves to Card info activity
    public void ToCards(String cardnumber){
        Intent newIntent= new Intent(userCards.this, CardInfo.class);
        cardnumber= StringU.getCardNumber(cardnumber);
        BankCard proxyCard= account.getACard(cardnumber);
        newIntent.putExtra("BankCard", proxyCard);
        this.finish();
        userCards.this.startActivity(newIntent);
    }
}
