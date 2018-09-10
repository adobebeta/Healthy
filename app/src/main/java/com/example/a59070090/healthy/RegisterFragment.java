package com.example.a59070090.healthy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fregment_register, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRegisterBtn();
    }

    void initRegisterBtn(){
        Button _registerBtn = (Button)getView().findViewById(R.id.register_rergister_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _email = (EditText) getView().findViewById(R.id.register_email);
                EditText _password = (EditText) getView().findViewById(R.id.register_password);
                EditText _repassword = (EditText) getView().findViewById(R.id.register_repassword);



                String emailStr = _email.getText().toString();
                String passwordStr = _password.getText().toString();
                String repasswordStr = _repassword.getText().toString();

                if(passwordStr.length() >= 6 && passwordStr.equals(repasswordStr)){
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("Register","SUCCESS");
                            sendVerifiedEmail(authResult.getUser());
                            Toast.makeText(getActivity() , "สำเร็จ" , Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Register","FAIL");
                            Toast.makeText(getActivity() , "กรุณาลองใหม่อีกครั้ง" , Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }

    private void sendVerifiedEmail(FirebaseUser _user){
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}
