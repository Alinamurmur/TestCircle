package com.example.alink.test;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {

    public static  final int DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new 	RealmConfiguration.Builder(getApplicationContext())
                .name("app.realm")
               // .setModules(Realm.getDefaultModule(), new  MyLibraryModule())
                .schemaVersion(DB_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
