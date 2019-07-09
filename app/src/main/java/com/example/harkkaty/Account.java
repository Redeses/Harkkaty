package com.example.harkkaty;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    private ArrayList<AccountEvents> event;
    private ArrayList<BankCard> cards;
    private Double currentBalance;
    private String type;
    private String ID;
    private AccountEvents accEvent;
    private SQLUtility sql;

    public Account(){
        event=null;
        currentBalance = 0.0;
        type="CheckingAccount";
        ID="";
        accEvent = null;
    }

    public void addEvent(Date time, String theOtherAccount, double amount){
        accEvent = new AccountEvents();
        accEvent.AccountEvents(time, theOtherAccount, amount);
        event.add(accEvent);
    }

    public void setAccount(String accountID, int saving, String balanceString){
        ID = accountID;
        double balance = Double.parseDouble(balanceString);
        if(saving == 1){
            type = "SavingsAccount";
        }else{

        }
        currentBalance = balance;
        getCards();
    }

    private void getCards(){
        sql.getCards(ID);
    }

}
