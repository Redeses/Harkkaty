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

    /*method that allows a Account object to be made into a form of
    = AccountNumber
    €          accountBalance
    */
    public String makeAccountToString(Account acc){
        String proxy="";
        proxy= acc.getAccountNumber() + "/n €   "+ acc.getBalance();
        return proxy;
    }


    //helper method for making the accountID
    private String makeFourDigitNumber(){
        int random;
        String proxy="";
        while (proxy.length()<4){
            random = (int) (Math.random()*(10));
            proxy=proxy+ Integer.toString(random);
        }
        return proxy;
    }

    //next if method which makes a id for account AKA a bankNumber so only digits are allowed
    public String makeAccountID(String userName, boolean type){
        String proxy = "1267";
        if(type){
            proxy= proxy+" 1550";
        }else{
            proxy=proxy+" 4787";
        }
        int size = userName.length();
        proxy =proxy+ " "+ makeFourDigitNumber()+ " "+ makeFourDigitNumber();
        return proxy;
    }
}
