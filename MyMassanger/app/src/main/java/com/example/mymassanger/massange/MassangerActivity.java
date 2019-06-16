package com.example.mymassanger.massange;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mymassanger.R;
import com.example.mymassanger.users.UserListActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Calendar;


public class MassangerActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_send;
    EditText ed_text;
    ArrayList<MassangerItem> list;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference referans;
    MasRecAdapter adapter;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massanger);
        btn_send = findViewById(R.id.bnt_send);
        ed_text = findViewById(R.id.mass_ed);
        btn_send.setOnClickListener(this);
        key = getIntent().getStringExtra("key");
        database = FirebaseDatabase.getInstance();
        referans = database.getReference("massange").child(UserListActivity.KEY+key);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.res_view_mass1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MasRecAdapter(list);
        recyclerView.setAdapter(adapter);
        updateLiset();
    }



    @Override
    public void onClick(View v) {
        if (!ed_text.getText().toString().isEmpty()) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("massange");
            MassangerItem item = new MassangerItem(ed_text.getText().toString(),UserListActivity.name);
            myRef.child(key+UserListActivity.KEY).child(String.valueOf(System.currentTimeMillis())).setValue(item);
            myRef.child(UserListActivity.KEY+key).child(String.valueOf(Calendar.getInstance().getTime())).setValue(item);

            FirebaseDatabase database1 = FirebaseDatabase.getInstance();
            DatabaseReference myRef1 = database1.getReference("notification");
            myRef1.child(key).setValue("send");
            myRef1.child(key+1).setValue(UserListActivity.name);

            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference myRef2 = database2.getReference("notification");
            myRef2.child(key+2).setValue(ed_text.getText().toString());
            ed_text.setText("");

        } else {
            ed_text.setError("mutqagreq sms");
        }


    }

    public void updateLiset() {
        referans.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                list.add(dataSnapshot.getValue(MassangerItem.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MassangerItem user1 = dataSnapshot.getValue(MassangerItem.class);
                int index = getitem(user1);
                list.set(index, user1);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                MassangerItem user1 = dataSnapshot.getValue(MassangerItem.class);
                int index = getitem(user1);
                list.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private int getitem(MassangerItem user) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).mass.equals(user.getMassange())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }
}
