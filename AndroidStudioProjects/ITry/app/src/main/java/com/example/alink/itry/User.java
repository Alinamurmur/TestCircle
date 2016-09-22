package com.example.alink.itry;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User extends RealmObject {
    String name, time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

