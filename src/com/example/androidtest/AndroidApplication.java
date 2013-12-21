package com.example.androidtest;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by kumar on 9/21/13.
 */
public class AndroidApplication extends Application {

    public void onCreate(){

        super.onCreate();
        Toast.makeText(getApplicationContext(),"Welcome to My android Application", Toast.LENGTH_LONG).show();
    }
}
