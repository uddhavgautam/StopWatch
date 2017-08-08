package com.example.uddhav.stopwatch.View.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.uddhav.stopwatch.Controller.Utilities.MySQLiteHelper;
import com.example.uddhav.stopwatch.Model.POJO.StopWatch;
import com.example.uddhav.stopwatch.Model.POJO.StopWatchHistory;
import com.example.uddhav.stopwatch.R;

import static android.content.ContentValues.TAG;

public class StopWatchAddRemoveFragment extends Fragment implements View.OnClickListener {

    public static int addCount = 0, removeCount = 0;
    public static CountDownTimer countDownTimer;
    public static long lastSavedTime;
    private Button addBtn, removeBtn, toggleBtn;
    private Spinner spinner;
    private String mUserName; /* below TextWatcher makes user can't have null mUserName */
    private long totalTime = 0;
    private StopWatch stopWatch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //draw the fragment (Create GUI of fragment)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_watch_add_remove, container, false); //recycler_view_frag1 is a fragment
        rootView.setTag(TAG);

        addBtn = rootView.findViewById(R.id.addBtnSWAR);
        removeBtn = rootView.findViewById(R.id.removeBtnSWAR);
        spinner = rootView.findViewById(R.id.spinnerSWAR);


        //set up the click listener

        //Add button click creates new Instance of StopWatch. Every StopWatch is associated with the user
        // We should ask to input username on Add Button click.
        addBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        removeBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        removeBtn.setEnabled(false);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == addBtn.getId()) {
            removeBtn.setEnabled(true);
            mUserName = promptUsername(); //prompt username from user and add it
        } else if (view.getId() == removeBtn.getId()) {
            //new database added. We have to reflect it in StopWatchHistoryFragment
            StopWatchHistory stopWatchHistory;
            StopWatchAddRemoveFragment.countDownTimer.cancel();
            if (spinner.getSelectedItem().toString().matches("second")) {
                stopWatchHistory = new StopWatchHistory(lastSavedTime + " seconds for " + mUserName);
            } else {
                stopWatchHistory = new StopWatchHistory(lastSavedTime + " milli-seconds for " + mUserName);
            }
            StopWatchHistoryFragment.stopWatchHistoryAdapter.insert(0, stopWatchHistory);
            StopWatchFragment.stopWatchAdapter.remove();
            removeBtn.setEnabled(false);
            addBtn.setEnabled(true);
            spinner.setEnabled(true);
            addCount--;
        } else {
            //do nothing
        }
    }

    private int getTotalTime() {
        if (spinner.getSelectedItem().toString().matches("second")) {

        }
        return 0;
    }


    private String promptUsername() {

        LayoutInflater li = LayoutInflater.from(getActivity().getApplicationContext());
        View promptsView = li.inflate(R.layout.alert_dialog, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Username");
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mUserName = userInput.getText().toString(); //I get non-null username
                                startCounter(addCount, mUserName);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        userInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //XXX do something
                if (userInput.getText().length() > 0) {
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }
        });

        // show it
        alertDialog.show();
        addBtn.setEnabled(false);
        spinner.setEnabled(false);

        return mUserName;

    }

    private long startCounter(final int size, final String mUserName) {
        totalTime = 0; //reset the time
        Log.i("user", mUserName);
        Log.i("time", totalTime + "");
        Log.i("size", totalTime + "");

        stopWatch = new StopWatch(totalTime + "", mUserName);
        StopWatchFragment.stopWatchAdapter.insert(size, stopWatch); //start always from zero, and inputted userName


        if (spinner.getSelectedItem().toString().matches("second")) {
            countDownTimer = new CountDownTimer(1000000000, 1000) {
                @Override
                public void onTick(long l) {
                    totalTime = l;
                    //just update time textview of row
                    long value = (1000000000 - totalTime) / 1000;
                    lastSavedTime = value;
                    StopWatchFragment.stopWatchAdapter.doPartialUpdate(size, value);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            countDownTimer = new CountDownTimer(1000000000, 1) {
                @Override
                public void onTick(long l) {
                    totalTime = l;
                    //just update time textview of row
                    long value = 1000000000 - totalTime;
                    lastSavedTime = value;
                    StopWatchFragment.stopWatchAdapter.doPartialUpdate(size, value);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }


        //save StopWatch instance in database
        if (spinner.getSelectedItem().toString().matches("second")) {
            stopWatch = new StopWatch(lastSavedTime + " seconds", mUserName); //updated StopWatch

            //String: timeValue in second
            MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getActivity().getApplicationContext());
            mySQLiteHelper.addStopWatch(stopWatch);

        } else {
            //String: timeValue in milli-second

            stopWatch = new StopWatch(lastSavedTime + " milli-seconds", mUserName); //updated StopWatch

            MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getActivity().getApplicationContext());
            mySQLiteHelper.addStopWatch(stopWatch);
        }

        addCount++;
        return totalTime;
    }
}


