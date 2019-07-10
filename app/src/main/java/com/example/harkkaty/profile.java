package com.example.harkkaty;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
//Fragemnt for changing user information
public class profile extends Fragment {
    private TextView name, birthdate, address, phone, email, username;
    private Intent intent;
    private User us;

    private ArrayList<String> allnfolist;

    private SQLUtility sql;
    private Fragment infoFrag;


    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFrag = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (TextView) viewFrag.findViewById(R.id.NameI);
        birthdate= (TextView) viewFrag.findViewById(R.id.dateI);
        address = (TextView) viewFrag.findViewById(R.id.adressI);
        phone = (TextView) viewFrag.findViewById(R.id.phoneI);
        email = (TextView) viewFrag.findViewById(R.id.emailI);
        username= (TextView) viewFrag.findViewById(R.id.userI);
        sql=sql.getSQLUtil(this.getContext());
        getInfo();
        return viewFrag;
    }

    @Override
    public void onResume(){
        super.onResume();
        getInfo();
    }


    //todo make a method that adds all the info to be used in profile
    public void getInfo(){
        us=us.getCurrentUser();
        Intent intent = new Intent();

        String ID = intent.getStringExtra("ID");

        //the order the info lists info is name(full), birthdate, country, address, email, phonenumber
        allnfolist=us.getAllInfo();
        name.setText(allnfolist.get(0));
        birthdate.setText(allnfolist.get(1));
        address.setText(allnfolist.get(3)+" "+ allnfolist.get(2));
        email.setText(allnfolist.get(4));
        phone.setText(allnfolist.get(5));
    }

    public void changePassword(View view){
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragtrans = fragManager.beginTransaction();
        infoFrag = new password();
        fragtrans.addToBackStack("profile");
        fragtrans.hide(profile.this);
        fragtrans.replace(R.id.profileSettings, infoFrag);
        fragtrans.commit();
    }

    public void changeInfo(View v){
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragtrans = fragManager.beginTransaction();
        infoFrag = new infoChange();
        fragtrans.addToBackStack("profile");
        fragtrans.hide(profile.this);
        fragtrans.replace(R.id.profileSettings, infoFrag);
        fragtrans.commit();
    }

}