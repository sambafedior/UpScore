package com.sambafedior.upscore.utils;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    private static Context context;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext() {
        return context;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
