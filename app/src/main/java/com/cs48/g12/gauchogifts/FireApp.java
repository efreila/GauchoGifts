package com.cs48.g12.gauchogifts;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Kate on 2/19/17.
 */

//Necessary to make a connection to the data on Firebase
public class FireApp extends Application{

    @Override
    public void onCreate()  {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
