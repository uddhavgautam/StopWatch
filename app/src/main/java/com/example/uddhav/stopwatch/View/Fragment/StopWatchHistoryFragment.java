package com.example.uddhav.stopwatch.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uddhav.stopwatch.Controller.RecyclerViewAdapter.StopWatchHistoryAdapter;
import com.example.uddhav.stopwatch.Controller.Utilities.MySQLiteHelper;
import com.example.uddhav.stopwatch.Model.POJO.StopWatch;
import com.example.uddhav.stopwatch.Model.POJO.StopWatchHistory;
import com.example.uddhav.stopwatch.R;

import java.util.ArrayList;
import java.util.List;

public class StopWatchHistoryFragment extends Fragment {
    private static final String TAG = "StopWatchHistoryFragment";
    public static StopWatchHistoryAdapter stopWatchHistoryAdapter;
    public static Context stopWatchHistoryContext;
    protected RecyclerView stopWatchHistoryRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private List<StopWatchHistory> stopWatchHistoryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWatchHistoryList = new ArrayList<>();
    }

    //draw the fragment (Create GUI of fragment)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stop_watch_history, container, false); //recycler_view_frag1 is a fragment
        rootView.setTag(TAG);

        stopWatchHistoryContext = getActivity().getApplicationContext(); //set the context

        stopWatchHistoryRecyclerView = rootView.findViewById(R.id.recyclerViewHistory);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        stopWatchHistoryRecyclerView.setLayoutManager(mLayoutManager);
        stopWatchHistoryAdapter = new StopWatchHistoryAdapter(stopWatchHistoryList, stopWatchHistoryContext);
        stopWatchHistoryRecyclerView.setAdapter(stopWatchHistoryAdapter); //now Adapter's activity starts

        return rootView;
    }

    @Override
    public void onStart() { //onStart comes after onCreateView() callback
        super.onStart();

        //fetch all StopWatch data from database
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getActivity().getApplicationContext());
        List<StopWatch> fetchedData = mySQLiteHelper.getAllStopWatch();
        int i = 0;
        for (StopWatch stopWatch : fetchedData) {
            StopWatchHistoryFragment.stopWatchHistoryAdapter.insert(i, new StopWatchHistory(stopWatch.getTotalTime() + " for " + stopWatch.getUser()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

}