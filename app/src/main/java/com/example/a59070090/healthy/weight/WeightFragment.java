package com.example.a59070090.healthy.weight;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070090.healthy.R;

import java.util.ArrayList;

//import com.example.a59070090.healthy.R;

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
        weights.add((new Weight("04 Jan 2018",79,"UP")));


        ListView weightList = (ListView) getView().findViewById(R.id.weight_list); //id
        WeightAdapter weightAdapter = new WeightAdapter(getActivity(), android.R.layout.list_content ,weights);
        weightList.setAdapter(weightAdapter);

        Button _addBtn = getView().findViewById(R.id.add_weight);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
