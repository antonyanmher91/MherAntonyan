package com.example.mymassanger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mymassanger.users.UserListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    LoginFr loginFr;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loadText();


    }

    public void loadText(){
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        String login =sharedPreferences.getString("login","aaa@aaa.ru");
        String pass = sharedPreferences.getString("pass","aaaaaaa");
        Log.i("==p", "loadText: "+login+"  "+pass);

        if (!login.isEmpty()&&!pass.isEmpty()){
            singin(login,pass);
        }else {
            loginFragment();
        }
    }
    public void singin(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this
                        , new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                                    intent.putExtra("login",email);
                                    intent.putExtra("KEY",user.getUid());
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    loginFragment();


                                }

                                // ...
                            }
                        });
    }

    private void loginFragment() {
        fragmentManager = getSupportFragmentManager();
        loginFr = new LoginFr();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.LLmain, loginFr);
        fragmentTransaction.commit();
    }

}




