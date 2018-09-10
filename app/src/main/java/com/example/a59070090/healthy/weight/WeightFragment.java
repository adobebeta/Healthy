package com.example.a59070090.healthy.weight;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.a59070090.healthy.R;

import java.util.ArrayList;

//import com.example.a59070090.healthy.R;

/**
 * Created by LAB203_04 on 27/8/2561.
 */

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weights.add((new Weight("01 Jan 2018",63,"UP")));
        weights.add((new Weight("02 Jan 2018",67,"UP")));
        weights.add((new Weight("03 Jan 2018",69,"UP")));

        ListView weightList = (ListView) getView().findViewById(R.id.weight_list); //id
        WeightAdapter weightAdapter = new WeightAdapter(getActivity(), android.R.layout.list_content ,weights);
        weightList.setAdapter(weightAdapter);
    }
}
