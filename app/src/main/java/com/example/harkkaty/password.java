package com.example.harkkaty;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class password extends Fragment {

    private EditText password, newPassword, ReNewPassword;
    private String newpassword, ID;
    private SQLUtility sql;


    public password() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View passwordFragView = inflater.inflate(R.layout.fragment_password, container, false);
        password = passwordFragView.findViewById(R.id.oldP);
        newPassword = passwordFragView.findViewById(R.id.newP);
        ReNewPassword = passwordFragView.findViewById(R.id.reNewP);
        Intent intent = new Intent();
        ID= intent.getStringExtra("ID");
        return passwordFragView;
    }

    public void goBack(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(password.this);
        manager.popBackStack();
        transaction.commit();
    }

    public void changePassword(){
        if (checkTexts()==false){
            return;
        }else if(!newPassword.getText().toString().equals(ReNewPassword.getText().toString())){
            //todo toast here
            return;
        }else if(sql.checkPassword(ID, newPassword.toString().toString())){
            //todo toast here
            return;
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(password.this);
        manager.popBackStack();
        transaction.commit();
    }

    private boolean checkTexts(){
        if(password.getText().toString()==""){
            return false;
        }else if (password.getText().toString()==""){
            return false;
        }else if (ReNewPassword.getText().toString()==""){
            return false;
        }

        return true;
    }

}
