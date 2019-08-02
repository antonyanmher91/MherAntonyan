package com.example.myapplication.fragment;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.myapplication.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.adapter.Users_Adapter;
import com.example.myapplication.model.User;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Users_Fragment extends Fragment {
    private ProgressDialog dialog;
    private RecyclerView recyclerView;
    private Users_Adapter recyclerAdapter;
    private List<User> users;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public Users_Fragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_users, container, false);
        androidx.appcompat.widget.Toolbar toolbar =  view.findViewById(R.id.tool);
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        activity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        dialog= new ProgressDialog(getActivity());
        dialog.setMessage("please wait");
        dialog.show();
        recyclerView = view.findViewById(R.id.rec_view_user);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        new MyAsinqtask().execute();
        return view;
    }
    @SuppressLint("StaticFieldLeak")
    class MyAsinqtask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<List<User>> user = apiInterface.getUser();
            user.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(@NonNull Call<List<User>> call1, @NonNull Response<List<User>> response2) {
                    users = response2.body();
                    recyclerAdapter = new Users_Adapter(users);
                    recyclerView.setAdapter(recyclerAdapter);
                    dialog.dismiss();
                }

                @Override
                public void onFailure(@NonNull Call<List<User>> call1, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });



            return null;
        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.search,menu);
        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = null;
        if (search!=null){
            searchView= (SearchView) search.getActionView();
        }
        if (searchView!=null){
            assert  searchManager !=null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    recyclerAdapter.getFilter().filter(s);
                    return false;
                }
            });
        }
    }

}
