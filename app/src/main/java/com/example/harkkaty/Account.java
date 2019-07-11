package com.example.harkkaty;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Account implements Serializable {
    private ArrayList<AccountEvents> event;
    private ArrayList<BankCard> cards;
    private Double currentBalance;
    private String type;
    private String ID;
    private AccountEvents accEvent;
    private SQLUtility sql;
    private XML_Utility xml;

    public Account(){
        event=null;
        currentBalance = 0.0;
        type="CheckingAccount";
        ID="";
        accEvent = null;
        cards=null;
        xml = XML_Utility.getInstance();
    }


    //adds event to accounts to accounts events
    public void addEvent(Date time, String theOtherAccount, double amount, String message, String entity){
        accEvent = new AccountEvents();
        accEvent.AccountEvents(time, theOtherAccount, amount, message, entity);
        event.add(accEvent);
        sql.addMoney(theOtherAccount);
        xml.addEvent(time, theOtherAccount, amount, message, entity, ID);//Todo xml things

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
        //todo make this work, aslo make sure they are from newest to oldest
        events=xml.getEvents(ID);//should return a event list
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

    public void removeMoney(double money){
        currentBalance=currentBalance-money;
        sql.removeMoney(getAccountNumber());
    }

    public int getCardSize(){
        return cards.size();
    }


}
