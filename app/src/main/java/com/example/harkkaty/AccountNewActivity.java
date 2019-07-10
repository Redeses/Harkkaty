package com.example.harkkaty;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.List;

public class AccountNewActivity extends AppCompatActivity {
    private Spinner accountSpinner;
    private FrameLayout frameLayout;
    private Fragment fragment;
    private FragmentManager manager;
    private User user;
    private Account account;
    private ListUtility listU;
    Intent intent;

    private String proxy;

    //int that allows to keep chekc which fragment is active 1 = payment, 2= Cash, 3= AddMoney
    private int fragmentKeepr=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_new);
        frameLayout = findViewById(R.id.variableVIew);
        frameLayout.setBackgroundColor(Color.WHITE);
        frameLayout.setVisibility(View.INVISIBLE);
        user= user.getCurrentUser();
        listU= listU.getListUtility();
        setSpinnet();
    }

    public void startPaymentFragment(View v){
        if(manager.getFragments().isEmpty()){
            frameLayout.setVisibility(View.VISIBLE);
            fragment = new payment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=1;
        }else if(fragmentKeepr==1) {
            frameLayout.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
            fragmentKeepr=0;
        }
        else{
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            fragment = new payment();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=1;
        }
    }

    public void startCashFragment(View v){
        if(manager.getFragments().isEmpty()){
            frameLayout.setVisibility(View.VISIBLE);
            fragment = new Cash();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=2;
        }else if(fragmentKeepr==2){
            frameLayout.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
            fragmentKeepr=0;
        }
        else{

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            fragment = new Cash();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=1;
        }
    }

    public void startAddMoneyFragment(View v){
        if(manager.getFragments().isEmpty()){
            frameLayout.setVisibility(View.VISIBLE);
            fragment = new AddMoney();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=3;
        }else if(fragmentKeepr==3){
            frameLayout.setVisibility(View.INVISIBLE);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            transaction.commit();
            fragmentKeepr=0;
        }
        else{
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.detach(fragment);
            fragment = new AddMoney();
            transaction.replace(R.id.variableVIew, fragment);
            transaction.commit();
            fragmentKeepr=3;
        }
    }

    private void setSpinnet(){
        String[] str = new String[user.getAccountAmount()];
        //the proxy is used to find account selected

        str= listU.MakeAccountList(user.getAccountAmount());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, str);
        accountSpinner.setSelection(0);
        proxy= accountSpinner.getSelectedItem().toString();
        account=user.getSelectedAccount(proxy);
        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                proxy= accountSpinner.getSelectedItem().toString();
                account=user.getSelectedAccount(proxy);
                intent=new Intent();
                intent.putExtra("accountID", account);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goBack(View v){
        Intent newIntent= new Intent(AccountNewActivity.this, AccountActivity.class);
        this.finish();
        AccountNewActivity.this.startActivity(newIntent);
    }

    public void goHome(View v){
        Intent newIntent= new Intent(AccountNewActivity.this, Home.class);
        this.finish();
        AccountNewActivity.this.startActivity(newIntent);
    }

}
