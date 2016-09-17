package com.example.alink.test;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class Base extends RealmObject{
String nameUser,timeUser;

    public void setName(String name) {
        this.nameUser = name;
    }

    public void setTime(String time) {
        this.timeUser = time;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getTimeUser() {
        return timeUser;
    }
}
