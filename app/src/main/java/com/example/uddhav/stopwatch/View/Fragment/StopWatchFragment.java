package com.example.uddhav.stopwatch.View.Fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uddhav.stopwatch.Controller.RecyclerViewAdapter.StopWatchAdapter;
import com.example.uddhav.stopwatch.Model.POJO.StopWatch;
import com.example.uddhav.stopwatch.R;

import java.util.ArrayList;
import java.util.List;


public class StopWatchFragment extends Fragment {
    private static final String TAG = "StopWatchFragment";
    public static StopWatchAdapter mAdapter;
    public static Context stopWatchContext;
    protected RecyclerView stopWatchRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private List<StopWatch> stopWatchList;
    private Button okBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWatchList = new ArrayList<>();
    }

    //draw the fragment (Create GUI of fragment)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_watch, container, false); //recycler_view_frag1 is a fragment
        rootView.setTag(TAG);

        stopWatchContext = getActivity().getApplicationContext(); //set the context

        stopWatchRecyclerView = rootView.findViewById(R.id.recyclerViewStopWatch);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        stopWatchRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StopWatchAdapter(stopWatchList, stopWatchContext);
        stopWatchRecyclerView.setAdapter(mAdapter); //now Adapter's activity starts


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