package com.example.a59070090.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070090.healthy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAB203_04 on 27/8/2561.
 */

public class WeightAdapter extends ArrayAdapter<Weight> {

    List<Weight> weights = new ArrayList<Weight>();
    Context context;

    public WeightAdapter(Context context, int resource, List<Weight> object) {
        super(context, resource,object);
        this.weights = object;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return ข้อมูลที่เราจะแสดง ณ ตำแหน่งนั้นๆ
        View weightItem =  LayoutInflater.from(context).inflate(R.layout.fragment_weight_item,parent,false);

        TextView date = (TextView) weightItem.findViewById(R.id.weight_item_date);
        TextView weight = (TextView) weightItem.findViewById(R.id.weight_item_weight);
        TextView status =  weightItem.findViewById(R.id.weight_item_status);

        date.setText(weights.get(position).getDate());
        weight.setText(weights.get(position).getWeight()+"");
//        status.setText(weights.get(position).getStatus());

        return weightItem;
    }
}
