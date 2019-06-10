package com.example.mymassanger;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFr extends Fragment implements View.OnClickListener {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    RegisterFr registerFr;
    Button btn_reg;
    Button btn_login;
    EditText login;
    EditText parol;
    private FirebaseAuth mAuth;
    public LoginFr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btn_reg=view.findViewById(R.id.btn_pass);
        btn_login=view.findViewById(R.id.btn_login);
        login=view.findViewById(R.id.et_login);
        parol=view.findViewById(R.id.et_pass);
        mAuth = FirebaseAuth.getInstance();
   btn_login.setOnClickListener(this);
   btn_reg.setOnClickListener(this);
//        btn_reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager= getFragmentManager();
//                registerFr= new RegisterFr();
//                fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.LLmain,registerFr);
//                fragmentTransaction.commit();
//            }
//        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        return  view;
    }

    public void singin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity()
                        , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(getContext(), UserListActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            login.setError("invalid");
                            parol.setError("invalid");

                        }

                        // ...
                    }
                });
    }
//
//    public void registracia(String email, String password){
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//
//                            Toast.makeText(getContext(), "hhhhh failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        // ...
//                    }
//                });
//    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_login:{
            if (login.getText().toString().isEmpty()||parol.getText().toString().isEmpty()){
                login.setError("invalid");
                parol.setError("invalid");
            }else {
            singin(login.getText().toString(),parol.getText().toString());}
            break;
        }
        case R.id.btn_pass:{
                 fragmentManager= getFragmentManager();
                registerFr= new RegisterFr();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LLmain,registerFr);
                fragmentTransaction.commit();
            break;
        }
    }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

}
