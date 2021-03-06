package com.example.mymassanger;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymassanger.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFr extends Fragment {

    EditText name, surName, mail, login, password, repassword;
    Button btn_registracia;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    public RegisterFr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        findId(view);
        mAuth = FirebaseAuth.getInstance();

        btn_registracia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                boolean namebol = true;
                boolean surNamebool = true;
                boolean mailbool = true;
                boolean loginbool = true;
                boolean passbool = true;
                boolean repasswordbool = true;
                if (name.getText().toString().length() < 3) {
                    name.setError("Invalide name");
                    namebol = false;
                }
                if (surName.getText().toString().length() < 5) {
                    surName.setError("Invalide Surname");
                    surNamebool = false;
                }
                if (mail.getText().toString().length() < 5 || !mail.getText().toString().substring(2).contains("@")) {
                    mail.setError("Invalide mail");
                    mailbool = false;
                }
                if (login.getText().toString().length() < 4) {
                    login.setError("Creat login min 4 simvole");
                    loginbool = false;
                }
                if (!passwordupercas(password.getText().toString()) || !passwordtolofercase(password.getText().toString()) ||
                        !paswordnambrs(password.getText().toString())) {
                    password.setError("Invalid password");
                    passbool = false;
                }
                if (!repassword.getText().toString().equals(password.getText().toString())) {
                    repassword.setError("chi hamapatasxanum");
                    repasswordbool = false;
                }
                if ((namebol && surNamebool && mailbool && loginbool && passbool && repasswordbool)) {


                    registracia(mail.getText().toString(), password.getText().toString());


                }


            }
        });

        return view;
    }


    private void findId(View view) {
        name = view.findViewById(R.id.name);
        surName = view.findViewById(R.id.surname);
        mail = view.findViewById(R.id.mail);
        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);
        repassword = view.findViewById(R.id.repassword);
        btn_registracia = view.findViewById(R.id.btn_reg_fr_registr);
    }


    public boolean passwordupercas(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.contains(password.substring(i, i + 1).toUpperCase())) {
                if (uperCaseLoveCase(password, i)) return false;

                return true;
            }
        }
        return false;

    }

    public boolean passwordtolofercase(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if (password.contains(password.substring(i, i + 1).toLowerCase())) {
                if (uperCaseLoveCase(password, i))
                    return true;
            }
        }
        return false;
    }


    public boolean paswordnambrs(String passwrord) {
        for (int i = 0; i < 10; i++) {
            if (passwrord.contains(String.valueOf(i))) {
                return true;
            }

        }
        return false;
    }

    private boolean uperCaseLoveCase(String password, int i) {
        for (int j = 0; j < 10; j++) {
            if (password.substring(i, i + 1).contains(String.valueOf(j))) {
                return true;
            }
        }
        return false;
    }

    public void registracia(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users");
                            User user1 = new User(name.getText().toString(), surName.getText().toString(), user.getUid());
                            myRef.child(user.getUid()).setValue(user1);

                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            LoginFr loginFr = new LoginFr();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.LLmain, loginFr);
                            fragmentTransaction.commit();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}