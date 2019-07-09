package com.example.harkkaty;

import java.util.Date;

public class AccountEvents {
    private Date eventDate;
    private String receivingAccount;
    private double amount;

    public AccountEvents(){
        eventDate=null;
        receivingAccount="";
        amount = 0.0;
    }

    public void AccountEvents(Date date, String Raccount, double Amount){
        eventDate= date;
        receivingAccount=Raccount;
        amount=Amount;
    }

    private void setDate(){
        //DateC.currentDate();
    }
}
