package com.example.a59070090.healthy.sleep;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070090.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class SleepAdapter extends ArrayAdapter<Sleep> {

    List<Sleep> sleep = new ArrayList();
    Context context;

    public SleepAdapter(@NonNull Context context, int resource, @NonNull List<Sleep> objects){
        super(context, resource, objects);
        this.sleep = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _sleepItem = LayoutInflater.from(context).inflate(R.layout.fragment_sleep_item, parent, false);
        TextView _date = _sleepItem.findViewById(R.id.sleep_item_date);
        TextView _diff = _sleepItem.findViewById(R.id.sleep_item_hour);
        TextView _sleep = _sleepItem.findViewById(R.id.sleep_item_time);

        Sleep _row = sleep.get(position);
        _date.setText(_row.getDate());
        _diff.setText(_row.getTimeDiff());
        _sleep.setText(_row.getTimeSleep()+" - "+_row.getTimeWake());

        return _sleepItem;

    }

}
