package com.example.a59070090.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a59070090.healthy.MenuFragment;
import com.example.a59070090.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by adobebeta on 17/9/2018 AD.
 */

public class WeightFormFragment extends Fragment{
    FirebaseFirestore _firestore;
    FirebaseAuth _auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _firestore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();
        initSaveButton();
        initBackButton();
    }

    void initBackButton(){
        Button _btn = getView().findViewById(R.id.weight_form_back_btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void initSaveButton(){
        Button _btn = getView().findViewById(R.id.weight_form_save_btn);
        _btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText _date = getView().findViewById(R.id.weight_form_date);
                EditText _weight = getView().findViewById(R.id.weight_form_weight);

                String _dateString = _date.getText().toString();
                String _weightString = _weight.getText().toString();

                String _uid = _auth.getCurrentUser().getUid();

                Weight _data = new Weight(_dateString,Integer.valueOf(_weightString), "UP");

                _firestore.collection("myfitness")
                .document(_uid).collection("weight")
                        .document(_dateString)
                        .set(_data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }
}


