package com.example.androidtest;

import android.app.Activity;
import android.view.Menu;

public class Base_Activity extends Activity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

}
