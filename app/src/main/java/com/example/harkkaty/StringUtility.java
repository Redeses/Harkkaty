package com.example.harkkaty;

//a class for string manipulation
public class StringUtility {

    private static StringUtility SUtil = new StringUtility();
    private String letters;

    private StringUtility(){
        letters = "ABCDEFGHIJKLMNOPQRSTYVWXYZ";
    }

    public static StringUtility getStringutility(){
        return SUtil;
    }


    private char randomLetter(){
        char letter;
        int proxy;
        proxy = (int) (Math.random()*((25-0)+1));
        letter = (char) letters.charAt(proxy);
        return letter;
    }

    //makes a user id
    public String makeID(String user){
        String ID="";
        char proxyChar;
        int proxyInt, lenghtUser;
        lenghtUser = user.length();
        ID= ID + randomLetter();
        proxyChar = user.charAt(lenghtUser-1);
        if (Character.isDigit(proxyChar)==true){
            proxyInt = (int) (Math.random()*(10));
            ID=ID + new Integer(proxyInt).toString();
        }else{
            ID = ID +randomLetter();
        }
        ID = ID + new Integer((int) (Math.random()*(99))).toString();
        ID = ID+ randomLetter();
        return ID;
    }

    // checks if email has one and only one of @ signs
    public boolean checkforAtSign(String email){
        char sign;
        int x=0;
        for (int i=0; i<email.length(); i++){
            sign=email.charAt(i);
            if ((sign=='@')==true){
                x++;
            }

        }
        if(x==1){
            return true;
        }
        else {
            return false;
        }
    }
}
