package com.example.myapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UserImagesAdapter;
import com.example.myapplication.adapter.UserProfilePostAdapter;
import com.example.myapplication.fragment.fragmentpageuser.UserImages;
import com.example.myapplication.fragment.fragmentpageuser.WebView_Fragment;
import com.example.myapplication.model.Pictures;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPageActivity extends AppCompatActivity implements OnMapReadyCallback {
    int id;
    String name;
    private RecyclerView recyclerView;
    private RecyclerView imgRecyclerView;
    private UserProfilePostAdapter recyclerAdapter;
    private ApiInterface apiInterface;
    private List<User> list;
    private List<Post> postList;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    TextView phone, email, addres, company, web;
    MapView mapView;
    GoogleMap map;
    CircleImageView img;
    CollapsingToolbarLayout colapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        creatRetrofit();


        findMapView(savedInstanceState);


        id = getIntent().getIntExtra("ID", 1);
        name = getIntent().getStringExtra("KEY");
        new MyAsyncTask().execute();
        findViewById();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebView();
            }
        });
        startImgrecyclerView();
        colapsing.setTitle(name);
        Pictures pictures = new Pictures();
        Picasso.get().load(pictures.getUrl(id - 1)).into(img);

        email.setOnClickListener(emailclick);
        phone.setOnClickListener(call);
        addres.setOnClickListener(addressClick);
        company.setOnClickListener(companyClick);
    }

    View.OnClickListener emailclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, list.get(0).getEmail());
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
    };

    View.OnClickListener call = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + list.get(0).getPhone()));
            startActivity(intent);
        }
    };

    View.OnClickListener companyClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                String escapedQuery  = URLEncoder.encode(list.get(0).getCompany().getName(), "UTF-8");
                Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
    };

    View.OnClickListener addressClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri gmmIntentUri = Uri.parse("geo:" + list.get(0).getAddress().getGeo().getLat() + "," + list.get(0).getAddress().getGeo().getLng());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    };

    private void startImgrecyclerView() {
        UserImages userImages = new UserImages();

        UserImagesAdapter userImagesAdapter = new UserImagesAdapter(userImages.getImages(id));
        imgRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imgRecyclerView.setAdapter(userImagesAdapter);
    }

    private void findViewById() {
        phone = findViewById(R.id.phoneuser);
        email = findViewById(R.id.emailuser);
        addres = findViewById(R.id.addresuser);
        company = findViewById(R.id.companyuser);
        web = findViewById(R.id.webuser);
        imgRecyclerView = findViewById(R.id.rec_view_img);
        recyclerView = findViewById(R.id.rec_user_posty);
        colapsing = findViewById(R.id.toolbar_layout);
        img = findViewById(R.id.fab);
    }

    private void findMapView(Bundle savedInstanceState) {
        mapView =  findViewById(R.id.mapwiew);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(this);
    }

    private void creatRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    private void openWebView() {
        WebView_Fragment webView_fragment = new WebView_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", list.get(0).getWebsite());
        webView_fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.LL, webView_fragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Call<List<User>> user = apiInterface.getUserID(id);
        user.enqueue(new Callback<List<User>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<User>> call1, @NonNull Response<List<User>> response2) {
                list = response2.body();
                assert list != null;
                User user1 = list.get(0);
                phone.setText(user1.getPhone());
                email.setText(user1.getEmail());
                addres.setText(user1.getAddress().getCity() + " " + user1.getAddress().getStreet() + " " + user1.getAddress().getSuite());
                web.setText(user1.getWebsite());
                company.setText(user1.getCompany().getName());
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.valueOf(user1.getAddress().getGeo().getLat()), Double.valueOf(user1.getAddress().getGeo().getLng())))
                        .title("Marker"));
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.valueOf(user1.getAddress().getGeo().getLat()), Double.valueOf(user1.getAddress().getGeo().getLng()))));

                map.setMinZoomPreference(15.0f);
                map.setMaxZoomPreference(16.0f);
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }

                map.setMyLocationEnabled(true);



            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call1, @NonNull Throwable t) {
                Toast.makeText(UserPageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Call<List<Post>> postUser = apiInterface.getPostUser(id);
            postUser.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(@NonNull Call<List<Post>> call1, @NonNull Response<List<Post>> response2) {
                    postList = response2.body();
                    recyclerAdapter = new UserProfilePostAdapter(postList, name, id,UserPageActivity.this );
                    recyclerView.setAdapter(recyclerAdapter);


                }

                @Override
                public void onFailure(@NonNull Call<List<Post>> call1, @NonNull Throwable t) {
                    Toast.makeText(UserPageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
