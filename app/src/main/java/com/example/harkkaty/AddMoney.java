package com.example.harkkaty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoney extends Fragment {

    private Account acc;

    public AddMoney() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cashFragView =inflater.inflate(R.layout.fragment_add_money, container, false);
        acc= (Account) getArguments().getSerializable("account");
        return cashFragView;
    }

    //used to set account from activitys spinner and resett the spinner
    public void setAcc(Account account){
        acc=account;
    }

}
