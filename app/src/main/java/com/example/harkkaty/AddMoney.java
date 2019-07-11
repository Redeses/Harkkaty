package com.example.harkkaty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class AddMoney extends Fragment {

    private Account acc;
    private EditText moneyAmount;

    public AddMoney() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cashFragView =inflater.inflate(R.layout.fragment_add_money, container, false);
        acc= (Account) getArguments().getSerializable("account");
        moneyAmount = cashFragView.findViewById(R.id.addMoney);
        return cashFragView;
    }

    //used to set account from activitys spinner and resett the spinner
    public void setAcc(Account account){
        acc=account;
    }

    public void addTheMoney(){
        String proxy = moneyAmount.getText().toString();
        if (proxy==""){
            //todo toast text here
            return;
        }
        acc.addMoney(Double.parseDouble(proxy));

    }

}
