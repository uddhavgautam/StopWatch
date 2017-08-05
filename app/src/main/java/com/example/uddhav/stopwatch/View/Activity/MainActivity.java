package com.example.uddhav.stopwatch.View.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.uddhav.stopwatch.R;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchAddRemoveFragment;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchFragment;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchHistoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start fragments
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //common transaction object


            /* add StopWatchAddRemove Fragment */
            StopWatchAddRemoveFragment stopWatchAddFragment = new StopWatchAddRemoveFragment(); //Note: creating the instance of Fragment, a dynamic object
            //instantiating fragment via default constructor
            transaction.replace(R.id.stopWatchAdderRemoverLayoutFrag, stopWatchAddFragment);

            StopWatchFragment stopWatchFragment = new StopWatchFragment();
            transaction.replace(R.id.stopWatchFrag, stopWatchAddFragment);

            StopWatchHistoryFragment stopWatchHistoryFragment = new StopWatchHistoryFragment();
            transaction.replace(R.id.stopWatchHistoryLayoutFrag, stopWatchHistoryFragment);


            transaction.commit(); //common commit

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
