package com.example.alink.itry;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setName("MyNameIs");
        user.setTime("09:09");
        realm.commitTransaction();


        RealmResults<User> users = realm.where(User.class).findAll();
        User[] resultArray = users.toArray(new User[]);




        /** User userr = realm.where(User.class).findFirst();
         // realm.copyToRealm(userr);
         TextView textView = (TextView)findViewById(R.id.text);
         textView.setText(userr.toString());
         **/


    }
}
