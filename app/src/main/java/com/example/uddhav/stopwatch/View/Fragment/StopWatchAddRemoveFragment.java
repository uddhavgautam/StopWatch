package com.example.uddhav.stopwatch.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.uddhav.stopwatch.R;

import static android.content.ContentValues.TAG;

public class StopWatchAddRemoveFragment extends Fragment implements View.OnClickListener {

    private Button addBtn, removeBtn;
    private Spinner spinner;
    private View.OnClickListener addClickListener, removeClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement the logic to ask username
            }
        };

        removeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement the logic to save every statistics in SqLite DB including username
            }
        };

    }

    //draw the fragment (Create GUI of fragment)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_watch_add_remove, container, false); //recycler_view_frag1 is a fragment
        rootView.setTag(TAG);

        addBtn = (Button) rootView.findViewById(R.id.addBtnSWAR);
        removeBtn = (Button) rootView.findViewById(R.id.removeBtnSWAR);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerSWAR);

        //set up the click listener

        //Add button click creates new Instance of StopWatch. Every StopWatch is associated with the user
        // We should ask to input username on Add Button click.
        addBtn.setOnClickListener(addClickListener);
        addBtn.setOnClickListener(removeClickListener);

        //Otherwise, We need to click double
        addBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() { //this is because single click only performs focus
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.performClick();
                }
            }
        });
        removeBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() { //this is because single click only performs focus
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.performClick();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }
}
