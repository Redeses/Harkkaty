package com.example.harkkaty;

//a class for string manipulation
public class StringUtility {

    private static StringUtility SUtil = new StringUtility();

    private StringUtility(){

    }

    public static StringUtility getStringutility(){
        return SUtil;
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
