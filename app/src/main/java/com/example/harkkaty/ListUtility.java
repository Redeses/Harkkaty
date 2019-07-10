package com.example.harkkaty;

import java.util.ArrayList;

public class ListUtility {
    private ListUtility util = new ListUtility();
    private User user;

    private StringUtility StringU;
    private ArrayList<Account> AccountProxy;

    private ListUtility(){
        user=user.getCurrentUser();
        StringU=StringUtility.getStringutility();
    }

    public ListUtility getListUtility(){
        return util;
    }

    //makes accounts information into a list that can be used while making a spinner in other method
    //currently used in AccountActivity
    public String[] MakeAccountList(int size){
        String[] str = new String[size];
        AccountProxy = user.getAccounts();
        for (int i=0; i<size; i++){
            str[i] = StringU.makeAccountToString(AccountProxy.get(i));
        }
        return str;
    }

}
