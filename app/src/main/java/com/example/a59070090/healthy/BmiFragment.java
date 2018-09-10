package com.example.a59070090.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BmiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fregment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCalculate();
        initBackBtn();
    }

    void initCalculate(){
        Button _calculateBtn = (Button) getView().findViewById(R.id.bmi_calculate_btn);
        _calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _height = (EditText) getView().findViewById(R.id.bmi_height);
                EditText _weight = (EditText) getView().findViewById(R.id.bmi_weight);
                TextView _bmi = (TextView) getView().findViewById(R.id.bmi_bmishow);
                String heightStr = (String)_height.getText().toString();
                String weightStr = (String)_weight.getText().toString();
                Float height = Float.parseFloat(heightStr);
                Float weight = Float.parseFloat(weightStr);
                Float bmi;

                if (heightStr.isEmpty() || weightStr.isEmpty()){
                    Log.d("BMI","FIELD NAME IS EMPTY");
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("BMI","BMI IS VAKUE");
                    height = height/100;
                    bmi = weight / (height * height);
                    String bmiStr = Float.toString(bmi);
                    _bmi.setText(bmiStr);
                }
            }
        });
    }

    void initBackBtn(){
        TextView _backBtn = (TextView) getView().findViewById(R.id.bmi_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
            }

        });
    }
}
