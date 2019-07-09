package com.example.harkkaty;

//class which has bankCards non important info on it
public class BankCard {

    private String cardNumber;
    //type is either credit, debit or credit&debit
    private String type;
    //in limits if the limit is a -1 then there is no limit
    private int onlineLimit;
    private int cashLimit;
    private int checkingLimit;
    private int credit;

    public BankCard(){
        cardNumber="default";
        type="";
        onlineLimit=-1;
        cashLimit=-1;
        checkingLimit=-1;
        credit =0;//no credit
    }


    public void setBankCard(String cardN, String type, int onlineLimit, int cashLimit, int checkingLimit, int credit){

    }
}
