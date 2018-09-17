package com.example.a59070090.healthy;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070090.healthy.weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MenuFragment extends Fragment{
    ArrayList<String> menu ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return null;
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        menu = new ArrayList<>();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("Setup");
        menu.add("Sign out");

        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>( //สร้าง adapter
                getActivity(), android.R.layout.simple_list_item_1, menu
        );

        ListView menuList = (ListView) getView().findViewById(R.id.menu_list);
        menuList.setAdapter(menuAdapter);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l){
              Log.d("MENU","Click on menu = "+ menu.get(i));
//                menuAdapter.notifyDataSetChanged();

                if (menu.get(i).equals("BMI")){
                    Log.d("USER","GOTO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BmiFragment()).addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Weight")){
                    Log.d("USER","GOTO WEIGHT");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Sign out")){
                    Log.d("USER","Sign out from firebase auth");
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new LoginFragment()).commit();
                }

          }
        });

    }


}
