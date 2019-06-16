package com.example.cragir_001.pakage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cragir_001.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.resviewmain2);
        List<User>userner=Userner();
        RecAdapterMain2 adapter = new RecAdapterMain2(userner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
    public List<User> Userner(){
        List<User> users= new ArrayList<>();
        User user = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user1 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user2 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user3 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user4 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user5 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user6 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user7 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user8 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user9 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user10 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user11 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user12 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user13 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user14 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user15 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user16 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user17 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user18 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user19 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user20 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user21 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user22 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user23 = new User("Hakob","Grigoryan",R.drawable.bmw);
        User user24 = new User("Hakob","Grigoryan",R.drawable.bmw);
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        users.add(user14);
        users.add(user15);
        users.add(user16);
        users.add(user17);
        users.add(user18);
        users.add(user19);
        users.add(user20);
        users.add(user21);
        users.add(user22);
        users.add(user23);
        users.add(user24);
        return users;

    }
}
