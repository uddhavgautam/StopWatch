package com.example.uddhav.stopwatch.Controller.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.uddhav.stopwatch.Model.POJO.StopWatch;
import com.example.uddhav.stopwatch.R;
import com.example.uddhav.stopwatch.View.Fragment.StopWatchAddRemoveFragment;

import java.util.Collections;
import java.util.List;

/**
 * Created by uddhav on 8/5/17.
 */

public class StopWatchAdapter extends RecyclerView.Adapter<StopWatchAdapter.StopWatchViewHolder> {
    private static final String TAG = "StopWatchAdapter";
    public static List<StopWatch> stopWatchList = Collections.emptyList(); //returns immutable empty list
    StopWatch stopWatch = new StopWatch();
    Context context;

    public StopWatchAdapter() {
    }

    public StopWatchAdapter(List<StopWatch> stopWatchList, Context stopWatchContext) {
        this.context = stopWatchContext;
        this.stopWatchList = stopWatchList;
    }

    public List<StopWatch> getStopWatchList() {
        return stopWatchList;
    }

    public void setStopWatchList(List<StopWatch> stopWatchList) {
        this.stopWatchList = stopWatchList;
    }

    @Override
    public StopWatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_stopwatch_recyclerview, parent, false);
        StopWatchViewHolder holder = new StopWatchViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(StopWatchViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(StopWatchViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            holder.updateUserNameAndValue();
        } else {
            for (Object payload : payloads) {
                if (payload instanceof Long) {
                    holder.bindPayload((Long) payload);
                } else {
                    // do nothing
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    //so to insert data in recycler view, we must use this insert method of here or we can create the equivalent insert method as below
    public void insert(int position, StopWatch data) {
        stopWatchList.add(position, data);
//        notifyItemInserted(listUrlDataGeneralInformation.size() - 1);
        notifyItemInserted(position);
    }

    public void doPartialUpdate(int position, Object payload) {
        notifyItemRangeChanged(position, 1, payload);
    }

    public void update(int position, StopWatch data) {
        stopWatchList.remove(position);
        notifyItemRemoved(position);

        insert(position, data);
    }

    //get item from predefined position
    public StopWatch getItemFromPredefinedPosition(int position) {
        return stopWatchList.get(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(StopWatch data) {
        int position = stopWatchList.indexOf(data);
        stopWatchList.remove(position);
        notifyItemRemoved(position);
    }

    public void remove() {
        stopWatchList.remove(0);
        notifyItemRemoved(0);
    }

    @Override
    public int getItemCount() {
        return stopWatchList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class StopWatchViewHolder extends RecyclerView.ViewHolder { //can be as separate class
        public static TextView dataTextView;
        public static TextView userTextView;
        public static ToggleButton toggleButton;
        private StopWatch stopWatch = new StopWatch();


        public StopWatchViewHolder(View v) {
            super(v);
            dataTextView = v.findViewById(R.id.dataTextView);
            userTextView = v.findViewById(R.id.userTextView);
            toggleButton = v.findViewById(R.id.toggleButton);

            dataTextView.setFocusable(false);
            dataTextView.setClickable(false);

            userTextView.setFocusable(false);
            userTextView.setClickable(false);

            toggleButton.setChecked(false);

            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (toggleButton.isChecked()) {
                        //start CountDownTimer
                        StopWatchAddRemoveFragment.countDownTimer.start();

                    } else {
                        // pause CountDownTimer
                        StopWatchAddRemoveFragment.countDownTimer.cancel();
                    }
                }
            });

        }

        public void bindPayload(Long payload) {
            dataTextView.setText("   " + payload + "");

        }

        public void updateUserNameAndValue() {
            int lastValue = stopWatchList.size() - 1;
            userTextView.setText("   " + stopWatchList.get(lastValue).getUser() + "");
            dataTextView.setText("   " + stopWatchList.get(lastValue).getTotalTime() + "");
            toggleButton.setChecked(true);

        }
    }

}
