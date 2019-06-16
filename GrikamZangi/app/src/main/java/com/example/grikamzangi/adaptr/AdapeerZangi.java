package com.example.grikamzangi.adaptr;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grikamzangi.fragment.Internet;
import com.example.grikamzangi.fragment.WhatsApp;
import com.example.grikamzangi.fragment.messenger;
import com.example.grikamzangi.fragment.sms;
import com.example.grikamzangi.fragment.zang;

public class AdapeerZangi extends FragmentPagerAdapter {
    static  final  int COUNT=5;

    public AdapeerZangi(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:return  zang.newInstance(i);
            case  1: return  new sms();
            case 2: return new WhatsApp();
            case 3: return new Internet();
            case 4: return  new messenger();
        }
        return  zang.newInstance(i);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "ZANG";
            case 1: return  "SEND SMS";
            case  2: return  "WhatsApp SMS";
            case  3: return  "Imternet";
            case 4: return "Messenger";
            default:return "";
        }

    }
}
