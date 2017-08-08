package com.example.uddhav.stopwatch.Controller.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uddhav.stopwatch.Model.POJO.StopWatchHistory;
import com.example.uddhav.stopwatch.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by uddhav on 8/5/17.
 */

public class StopWatchHistoryAdapter extends RecyclerView.Adapter<StopWatchHistoryAdapter.StopWatchHistoryViewHolder> {
    private static final String TAG = "StopWatchHistoryAdapter";
    public static List<StopWatchHistory> stopWatchHistoryList = Collections.emptyList(); //returns immutable empty list
    Context context;

    public StopWatchHistoryAdapter(List<StopWatchHistory> stopWatchHistoryList, Context stopWatchHistoryContext) {
        this.stopWatchHistoryList = stopWatchHistoryList;
        this.context = stopWatchHistoryContext;
    }

    public StopWatchHistoryAdapter() {
    }

    @Override
    public StopWatchHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_stopwatch_history_recyclerview, parent, false);
        StopWatchHistoryViewHolder holder = new StopWatchHistoryViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(StopWatchHistoryViewHolder holder, int position) {
        int lastValue = stopWatchHistoryList.size() - 1;
        Log.i("history ", lastValue + "");

        holder.geHistoryTextView().setText(stopWatchHistoryList.get(position).getHistory());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    //so to insert data in recycler view, we must use this insert method of here or we can create the equivalent insert method as below
    public void insert(int position, StopWatchHistory data) {
        stopWatchHistoryList.add(position, data);
//        notifyItemInserted(listUrlDataGeneralInformation.size() - 1);
        notifyItemInserted(position);
    }

    public void update(int position, StopWatchHistory data) {
        stopWatchHistoryList.remove(position);
        notifyItemRemoved(position);

        insert(position, data);
    }

    //get item from predefined position
    public StopWatchHistory getItemFromPredefinedPosition(int position) {
//        for(int i = 0;i<3;i++) {
//            Log.i("i: ", listUrlDataGeneralInformation.get(i).getCountDownTimer());
//        }
        return stopWatchHistoryList.get(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(StopWatchHistory data) {
        int position = stopWatchHistoryList.indexOf(data);
        stopWatchHistoryList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return stopWatchHistoryList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class StopWatchHistoryViewHolder extends RecyclerView.ViewHolder { //can be as separate class
        public static TextView historyTextView;

        public StopWatchHistoryViewHolder(View v) {
            super(v);
            historyTextView = v.findViewById(R.id.historyTextView);

        }

        public TextView geHistoryTextView() {
            return historyTextView;
        }


    }

}
