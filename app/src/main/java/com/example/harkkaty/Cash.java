package com.example.harkkaty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Cash extends Fragment {
    private EditText cashAmount;
    private Spinner type, card;
    private String typeString, cardString;
    private String[] str;

    //button used for hiding payment
    private Button makePayment;

    private ListUtility listU;
    private DateC date;

    private Account proxyAccount;

    public Cash() {
        listU = listU.getListUtility();
        date = date.getDatec();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cashFragView=inflater.inflate(R.layout.fragment_cash, container, false);
        cashAmount = cashFragView.findViewById(R.id.amountcash);
        type = cashFragView.findViewById(R.id.type);
        card = cashFragView.findViewById(R.id.cardSelected);
        makePayment = cashFragView.findViewById(R.id.makePayment);
        proxyAccount = (Account) getArguments().getSerializable("account");
        setSpinners();
        setAccountSpinner();
        return cashFragView;
    }

    //used to set account from activitys spinner and resett the spinner
    public void setAcc(Account account){
        proxyAccount=account;
        setAccountSpinner();
    }

    //sets the spinners when the fragment is created
    public void setSpinners() {
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeString = type.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    //sets the account spinner at the beginning of the run
     public void setAccountSpinner(){
        if (proxyAccount.getCardSize()==0){
            makePayment.setVisibility(View.INVISIBLE);
        }else {
            makePayment.setVisibility(View.VISIBLE);
        }
        str= listU.MakeCardList(proxyAccount);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, str);
        card.setSelection(0);
        card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardString = card.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //makes payment and and event
    public void makePayment(View view){
        String proxy=cashAmount.getText().toString();
        typeString = type.getSelectedItem().toString();
        if (proxy==""){
            //todo make toast here
            return;
        }
        double money = Double.parseDouble(proxy);
        if (money>proxyAccount.getBalance()){
            //toast here
            return;
        }
        proxyAccount.removeMoney(money);

        proxyAccount.addEvent(date.currentDate(), "thisAccount", money, "", typeString);

    }

}
