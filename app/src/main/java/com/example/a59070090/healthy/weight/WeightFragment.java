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
import android.widget.Toast;

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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

//import com.example.a59070090.healthy.R;

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public WeightFragment(){
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.weights = new ArrayList<>();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        weights.add((new Weight("01 Jan 2018",63,"UP")));
//        weights.add((new Weight("02 Jan 2018",67,"UP")));
//        weights.add((new Weight("03 Jan 2018",69,"UP")));
//        weights.add((new Weight("04 Jan 2018",79,"UP")));


//        ListView weightList = (ListView) getView().findViewById(R.id.weight_list); //id
//        WeightAdapter weightAdapter = new WeightAdapter(getActivity(), android.R.layout.list_content ,weights);
//        weightList.setAdapter(weightAdapter);

        addWeightBtn();
        getValueFromFirebase();
    }

    void addWeightBtn(){
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


    void getValueFromFirebase(){
        ListView weightList = (ListView) getView().findViewById(R.id.weight_list); //id
        final WeightAdapter weightAdapter = new WeightAdapter(getActivity(), android.R.layout.list_content ,weights);
        weightList.setAdapter(weightAdapter);
        weights.clear();

        firebaseFirestore
                .collection("myfitness")
                .document(firebaseAuth.getUid())
                .collection("weight")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            weights.add(doc.toObject(Weight.class));
                        }
                        weightAdapter.notifyDataSetChanged();
                        Log.d("HISTORY","Query from firestore and set to Arraylist");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HISTORY","QUERY ERROR");
                Toast.makeText(getActivity(), "Firestore Error!", Toast.LENGTH_SHORT).show();
            }

        });
    }


}
