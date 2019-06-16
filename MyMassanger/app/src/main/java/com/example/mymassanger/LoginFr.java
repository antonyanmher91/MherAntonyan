package com.example.mymassanger;


import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymassanger.massange.MassangerActivity;
import com.example.mymassanger.users.UserListActivity;
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
    private FirebaseAuth mAuth;
    RegisterFr registerFr;
    Button btn_reg;
    Button btn_login;
    EditText login;
    EditText parol;
    CheckBox save;
    SharedPreferences sharedPreferences;
    public LoginFr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btn_reg = view.findViewById(R.id.btn_pass);
        btn_login = view.findViewById(R.id.btn_login);
        login = view.findViewById(R.id.et_login);
        parol = view.findViewById(R.id.et_pass);
        save=view.findViewById(R.id.chek_b);
        mAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        loadText();
      save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  seveText();
              }
          }
      });


        return view;
    }

    public void singin(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity()
                        , new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(getContext(), UserListActivity.class);
                                    intent.putExtra("login",email);
                                    intent.putExtra("KEY",user.getUid());
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                if (login.getText().toString().isEmpty() || parol.getText().toString().isEmpty()) {
                    login.setError("invalid");
                    parol.setError("invalid");
                } else {
                    singin(login.getText().toString(), parol.getText().toString());
                }
                break;
            }
            case R.id.btn_pass: {
                fragmentManager = getFragmentManager();
                registerFr = new RegisterFr();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LLmain, registerFr);
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
    public void  seveText(){
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login",login.getText().toString());
        editor.putString("pass",parol.getText().toString());
        editor.commit();


    }
    public void loadText(){
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        String login =sharedPreferences.getString("login","");
        String pass = sharedPreferences.getString("pass","");
        if (!login.isEmpty()&&!pass.isEmpty()){
        singin(login,pass);}
    }

}
