package com.example.a59070090.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fregment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            mAuth = FirebaseAuth.getInstance();
            initLoginBtn();
            initRegisterBtn();
    }

        void initLoginBtn(){
        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn); //cast ให้ตรงกัน
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_user);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _emailStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();


                mAuth.signInWithEmailAndPassword(_emailStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                       if (authResult.getUser().isEmailVerified()){
                            Log.d("LOGIN","SUCCESS");
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
                        }
                        else{
                           Toast.makeText(getActivity() , "Please Confirm Your Email" , Toast.LENGTH_SHORT).show();
                       }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("LOGIN","FAIL");
                        Toast.makeText(getActivity() , "ERROR = "+e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    void initRegisterBtn(){
            TextView _registerBtn = (TextView) getView().findViewById(R.id.register_textview);
            _registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("USER", "GOTO REGIS");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
                }
            });
    }

}

