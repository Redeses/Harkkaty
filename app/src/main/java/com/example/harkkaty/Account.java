package com.example.harkkaty;

import android.widget.ArrayAdapter;

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
        cards=null;
    }

    public void addEvent(Date time, String theOtherAccount, double amount){
        accEvent = new AccountEvents();
        accEvent.AccountEvents(time, theOtherAccount, amount);
        event.add(accEvent);
    }

    //gotten when user info is also aquired
    public void setAccount(String accountID, int saving, String balanceString){
        ID = accountID;
        double balance = Double.parseDouble(balanceString);
        if(saving == 1){
            type = "SavingsAccount";
        }else{

        }
        currentBalance = balance;
        setCards();
        getEvents();
    }

    public Account getAccount(){
        return this;
    }

    //when accounts are gotten this also gets the realated cards
    private void setCards(){
        cards=sql.getCards(ID);
    }

    //returns cards
    public ArrayList<BankCard> getCards(){
        return cards;
    }

    //method which gets account events from xml
    //todo make xml functionality
    public ArrayList<AccountEvents> setEvents(){
        ArrayList<AccountEvents> events=null;
        //todo make this work
        //events=XML_Utility.getEvents(ID);//should return a event list
        return events;
    }

    //returns accoubt events
    public AccountEvents getEvents(){
        return accEvent;
    }

    //couple of methods that allows access to account information
    public String getAccountNumber(){
        return ID;
    }

    public double getBalance(){
        return currentBalance;
    }


}
