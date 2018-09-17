package com.example.a59070090.healthy.weight;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070090.healthy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

//import com.example.a59070090.healthy.R;

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();
    private FirebaseFirestore mdb;
    private FirebaseAuth _auth;

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
//        getData();

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

    void getData(){
        _auth = FirebaseAuth.getInstance();
        String uId = _auth.getUid();
        mdb = FirebaseFirestore.getInstance();

        mdb.collection("myfitness").document(uId).collection("weight").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        ArrayList<Weight> _weight = task.getResult();
                    }
                });

    }
}
