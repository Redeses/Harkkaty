package com.example.harkkaty;

import java.util.Date;


//class which holds event information
//if the current account is receiving then receiving is you and message is type is incoming;
public class AccountEvents {
    private Date eventDate;
    private String receivingAccount;
    private double amount;
    private String message, Entity;

    public AccountEvents(){
        eventDate=null;
        receivingAccount="";
        amount = 0.0;
        message="";
        Entity="";
    }

    public void AccountEvents(Date date, String Raccount, double Amount, String message, String entity){
        eventDate= date;
        receivingAccount=Raccount;
        amount=Amount;
        this.message = message;
        Entity=entity;

    }

    private void setDate(){
        //DateC.currentDate();
    }

    public Date getEventDate(){return eventDate;}

    public String getReceivingAccount(){
        return receivingAccount;
    }
    public double getAmount(){
        return amount;
    }
    public String getmessage(){
        return message;
    }
}
