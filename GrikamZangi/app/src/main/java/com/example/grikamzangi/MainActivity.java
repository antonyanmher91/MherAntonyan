package com.example.grikamzangi;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grikamzangi.User.User;
import com.example.grikamzangi.adaptr.AdapeerZangi;
import com.example.grikamzangi.adaptr.RycecAdapter;
import com.example.grikamzangi.fragment.zang;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ViewPager pager;
AdapeerZangi zang;
    List<User>list;
    Button button;
    RycecAdapter rycecAdapter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<User>list = new ArrayList<>();
        pager = findViewById(R.id.vP);
        zang = new AdapeerZangi(getSupportFragmentManager());
        pager.setAdapter(zang);
        User user = new User("Vahe","Aficeryan","094262027");
        User user1 = new User("Mher","Antonyan","095352505");
        list.add(user);
        list.add(user1);
        RecyclerView recyclerView = findViewById(R.id.recycler_pow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rycecAdapter=new RycecAdapter(list);
        recyclerView.setAdapter(rycecAdapter);
        TabLayout tabLayout = findViewById(R.id.TabLayout);
        tabLayout.setupWithViewPager(pager);
        button= findViewById(R.id.butobzang);

    }
    public void butonzang(View v){
        editText= findViewById(R.id.Edsms);
        Uri uri = Uri.parse("smsto:095352505");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        String str = String.valueOf(editText.getText());
        intent.putExtra("sms_body", str);
        startActivity(intent);
    }
    public void buttzang(View v){
        EditText editText1 = findViewById(R.id.Edzang);
        String str1 = String.valueOf(editText1.getText());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+str1));
        startActivity(intent);
    }
    public void whatsApp(View v){
        PackageManager pm=getPackageManager();
        EditText editText2=findViewById(R.id.edwatsapp);
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = String.valueOf(editText2.getText());

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void btnInternet(View v){
        EditText editText3= findViewById(R.id.Edint);
        String url = "http://www."+String.valueOf(editText3.getText());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void massenger(View v){
        EditText editText4 = findViewById(R.id.Edmess);
        String str4 = String.valueOf(editText4.getText());
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent
                .putExtra(Intent.EXTRA_TEXT,
                        str4);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.facebook.orca");
        try {
            startActivity(sendIntent);
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
        }
    }
}
