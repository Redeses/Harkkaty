package com.example.harkkaty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;



public class makeAccount extends Fragment {
    private EditText balance;
    private CheckBox savings;
    private User user;
    public makeAccount() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View makeAccountFrag =inflater.inflate(R.layout.fragment_make_account, container, false);
        balance = (EditText) makeAccountFrag.findViewById(R.id.giveBalance);
        savings = makeAccountFrag.findViewById(R.id.isSavings);
        user = user.getCurrentUser();//todo user user
        return makeAccountFrag;
    }

    public void makeTheAccount(View v){
        boolean saving=false;
        if (savings.isChecked()){
            saving = true;
         }
        if (balance.getText().toString().equals("")) {
            //todo toast here, that does not conflict with the previous
            return;
        }
        user.addAccount(saving, balance.getText().toString());
        //todo toast for showing that the account was succesfully done
        balance.setText("");
    }

}
