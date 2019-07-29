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
    private StringUtility stringU;
    private SQLUtility sql;
    private XML_Utility xml;
    private BankCard bc;

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
        accEvent.AccountEvents(time, theOtherAccount, amount, message, entity, getAccountNumber());
        event.add(accEvent);
        sql.addMoney(theOtherAccount, amount);
        sql.addEvent(accEvent);
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
        //events=xml.getEvents(ID);//should return a event list
        //events= sql.getEvents();
        return events;
    }

    //todo ????
    /*public ArrayList<BankCard> setCardsRecyc(){

    }*/

    //returns accoubt events
    public AccountEvents getEvents(){
        return accEvent;
    }

    //couple of methods that allows access to account information
    public String getAccountNumber(){
        return ID;
    }

    //returns the current balance of the account
    public double getBalance(){
        return currentBalance;
    }

    //adds money to teh account
    public void addMoney(double added){
        currentBalance=currentBalance+added;
        sql.updateMoney(ID,Double.toString(currentBalance));
    }

    //removes money from the account and the databse
    public void removeMoney(double money){
        currentBalance=currentBalance-money;
        sql.updateMoney(ID, Double.toString(currentBalance));
    }

    //returns the size of the bankcard list to be used
    public int getCardSize(){
        return cards.size();
    }


    //gets a card with a specific number from the list or return null if there isin't one
    public BankCard getACard(String number){
        BankCard proxy;
        for (int i=0; i<cards.size(); i++){
            proxy = cards.get(i);
            if(number ==proxy.getNumber()){
                return proxy;
            }
        }
        return null;

    }

    //todo make a thng that removes a certain object from a arraylist
    //removes card from the card list and the database
    public void removerCard(BankCard card){
        cards.remove(card);
        //sql.remove
    }

    //todo finish the ui cahin
    //adds a card to the aacount
    public void addCard(String checking, String online, String cash, String credit, String type){
        bc = new BankCard();
        bc.setBankCard(stringU.makeACardNumber(type), type, Integer.getInteger(online), Integer.getInteger(cash), Integer.getInteger(checking), Integer.getInteger(credit));
    }

}
