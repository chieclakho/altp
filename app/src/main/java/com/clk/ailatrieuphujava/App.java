package com.clk.ailatrieuphujava;

import android.app.Application;

import androidx.room.Room;

import com.clk.ailatrieuphujava.db.AppDB;

public class App extends Application {
    private static App instance;
    private AppDB db;
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
        db = Room.databaseBuilder(this, AppDB.class, "question")
                .createFromAsset("db/Question")
                .build();
    }

    public AppDB getDb() {
        return db;
    }

    public static App getInstance() {
        return instance;
    }

}
