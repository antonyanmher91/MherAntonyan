package com.example.mymassanger;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserListActivity extends AppCompatActivity {
  List<User> list;
  RecyclerView recyclerView;
  User user;
  FirebaseDatabase database;
  DatabaseReference referans;
  ResViewUserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
  database = FirebaseDatabase.getInstance();
  referans=database.getReference("users");
  list= new ArrayList<>();
  recyclerView= findViewById(R.id.rec_view_user);
  recyclerView.setHasFixedSize(true);
  LinearLayoutManager layoutManager= new LinearLayoutManager(this);
  recyclerView.setLayoutManager(layoutManager);
  adapter= new ResViewUserAdapter((ArrayList<User>) list);
  recyclerView.setAdapter(adapter);
  updateLiset();





        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // Read from the database

    }

    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }




  public  void updateLiset(){
        referans.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                list.add(dataSnapshot.getValue(User.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             User user1 = dataSnapshot.getValue(User.class);
             int index =getitem(user1);
             list.set(index,user1);
             adapter.notifyItemChanged(index);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                User user1 = dataSnapshot.getValue(User.class);
                int index =getitem(user1);
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
  private  int getitem(User user){
        int index =-1;
      for (int i = 0; i <list.size() ; i++) {
          if (list.get(i).key.equals(user.key)){
              index=i;
              break;
          }
      }
      return index;
  }


    }



