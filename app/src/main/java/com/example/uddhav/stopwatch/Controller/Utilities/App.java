package com.example.uddhav.stopwatch.Controller.Utilities;

import android.app.Application;
import android.content.Context;

/**
 * Created by uddhav on 8/7/17.
 */

public class App extends Application {
    public static Context AppContext;
    //bus is just a Otto bus. It uses handler for the communication between activity and fragments or activity and services.

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext(); //Now, AppContext is the context of the Application class (App class here)
    }
}
