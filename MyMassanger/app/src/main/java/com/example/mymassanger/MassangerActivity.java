package com.example.mymassanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MassangerActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_send;
    EditText ed_text;
    TextView txt;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massanger);
        btn_send= findViewById(R.id.bnt_send);
        ed_text= findViewById(R.id.mass_ed);
        txt=findViewById(R.id.mass_txt);
        btn_send.setOnClickListener(this);
        read("1");

    }


    public void writr(String str,String strmassange){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(str);

        myRef.setValue(strmassange);
    }

    public  void read(String str1){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(str1);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                str=str+value+"\n";
                Log.d("kkk", "Value is: " + value);
                txt.setText(str);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("kkk", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void onClick(View v) {
        writr("1",ed_text.getText().toString());
        ed_text.setText("");
    }
}
