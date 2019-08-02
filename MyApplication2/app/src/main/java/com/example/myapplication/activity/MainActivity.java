package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.fragment.Home_Fragment;
import com.example.myapplication.fragment.Users_Fragment;
import com.example.myapplication.fragment.Like_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;




public class MainActivity  extends AppCompatActivity {
    private BottomNavigationView navView;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (isNetworkConnected()){
            goToHomeFragment();
        }else {
            myAsyncTask=new MyAsyncTask();
            myAsyncTask.execute();
            Toast.makeText(this, "No Internet Conetcion", Toast.LENGTH_SHORT).show();
            navView.setVisibility(View.INVISIBLE);

        }

    }

    private void goToHomeFragment() {
        Home_Fragment hr = new Home_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,hr).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Home_Fragment hr = new Home_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,hr).commit();
                    return true;
                case R.id.navigation_dashboard:
                    Users_Fragment uf = new Users_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,uf).commit();
                    return true;
                case R.id.navigation_notifications:
                    Like_Fragment lf = new Like_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,lf).commit();
                    return true;
            }
            return false;
        }
    };

    private boolean isNetworkConnected() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal==0);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            navView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (true){
                if (isNetworkConnected()){
                    if (isCancelled()){
                        break;
                    }
                    goToHomeFragment();
                    break;
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            navView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
