package com.example.harkkaty;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TunnusLuonti extends Fragment {

    View UserInfoView;
    Button goToPersonalInfo, finish;
    private EditText User, password, repassword;
    private String userName, passwords;

    personalInfoUtility PUtility = personalInfoUtility.getInstance();


    public TunnusLuonti() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserInfoView = inflater.inflate(R.layout.fragment_tunnus_luonti, container, false);
        goToPersonalInfo = (Button) UserInfoView.findViewById(R.id.back);
        finish = (Button) UserInfoView.findViewById(R.id.done);
        User = (EditText) UserInfoView.findViewById(R.id.userinput);
        password= (EditText) UserInfoView.findViewById(R.id.passwordInput);
        repassword = (EditText) UserInfoView.findViewById(R.id.repassword);

        buttonlisteners();
        return UserInfoView;
    }

    private void buttonlisteners(){

        goToPersonalInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.detach(TunnusLuonti.this);
                manager.popBackStack();
                /*Fragment adInfo = manager.findFragmentByTag("addnfoView");
                transaction.replace(R.id.newUser, adInfo);*/
                transaction.commit();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  canSend=PersonUtil.checkInfo();
                if (canSend==false){
                    //TODO Toast method here here
                    return;
                }*/
                FisishUser();
            }
        });
    }
    //finishes making the user by sending info to a class and closes fragments
    private void FisishUser(){
        if(password.getText().toString().equals(repassword.getText().toString())){
            passwords=password.getText().toString();
        }else{
            //Todo show user that password is not the same, use also passwor utility to see if paswords is up to standards
            return;
        }
        if(PUtility.checkUser()==true){
            userName=User.getText().toString();
        }
        PUtility.makeAUser(userName, passwords);
        PUtility.emptylists();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(TunnusLuonti.this);
        Fragment adInfo = manager.findFragmentByTag("addnfoView");
        transaction.detach(adInfo);
        transaction.commit();

        final MainActivity activity = (MainActivity)getActivity();
        activity.cancleInfo();
    }



}
