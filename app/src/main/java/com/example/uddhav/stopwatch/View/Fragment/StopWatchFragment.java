package com.example.uddhav.stopwatch.View.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uddhav.stopwatch.Controller.RecyclerViewAdapter.StopWatchAdapter;
import com.example.uddhav.stopwatch.Model.POJO.StopWatch;
import com.example.uddhav.stopwatch.R;

import java.util.ArrayList;
import java.util.List;


public class StopWatchFragment extends Fragment {
    private static final String TAG = "StopWatchFragment";
    public static StopWatchAdapter stopWatchAdapter;
    public static Context stopWatchContext;
    public static RecyclerView stopWatchRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private List<StopWatch> stopWatchList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWatchList = new ArrayList<>();
    }

    //draw the fragment (Create GUI of fragment)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        rootView.setTag(TAG);

        stopWatchContext = getActivity().getApplicationContext(); //set the context

        stopWatchRecyclerView = rootView.findViewById(R.id.recyclerViewStopWatch);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        stopWatchRecyclerView.setLayoutManager(mLayoutManager);
        stopWatchAdapter = new StopWatchAdapter(stopWatchList, stopWatchContext);
        stopWatchRecyclerView.setAdapter(stopWatchAdapter); //adapter modified, when later I call this adapter, it would be neighbor of stopWatchRecylerView


        return rootView;
    }

    @Override
    public void onStart() { //onStart comes after onCreateView() callback
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

}