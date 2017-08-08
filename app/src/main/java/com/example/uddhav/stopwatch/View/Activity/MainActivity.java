package com.example.uddhav.stopwatch.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.uddhav.stopwatch.R;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchAddRemoveFragment;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchFragment;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchHistoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ((TextView) findViewById(R.id.main_toolbar_title)).setText("Stop-Watch!");



        //start fragments
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //common transaction object


            /* add StopWatchAddRemove Fragment */
            StopWatchAddRemoveFragment stopWatchAddFragment = new StopWatchAddRemoveFragment(); //Note: creating the instance of Fragment, a dynamic object
            //instantiating fragment via default constructor
            transaction.replace(R.id.stopWatchAdderRemoverLayoutFrag, stopWatchAddFragment);

            StopWatchFragment stopWatchFragment = new StopWatchFragment();
            transaction.replace(R.id.stopWatchLayoutFrag, stopWatchFragment);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stop_watch_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.AboutID:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
