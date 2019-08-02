package com.example.myapplication.model;

import java.util.ArrayList;

public class Pictures {
    public  String getUrl(int i){
        ArrayList<String> list = new ArrayList<>();
        list.add("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        list.add("https://image.shutterstock.com/z/stock-photo-portrait-of-a-smiling-man-125153402.jpg");
        list.add("https://s4.scoopwhoop.com/shon/random/dk1.jpg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRt8PyTGARehqzFSNIuNGeBL44du0usH-Y2vIQtZATv70WprmdAOg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEQ8R9T-KLaZfzmmGiEowwelBSil9Hic0PFT5OLwB6JAzf5eaa_g");
        list.add("https://i.dmarge.com/2016/09/long-hair-men-3-1057x1100.jpg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo3VgPLXlSDJNhg0It15jOUyFF_fCDc2UdsAgzPXxPZPYeNaIGLQ");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3sO248RG0DlymYJZrdB0nR2GqcGkNhdI_P1BO7DYmWjoNxa-1");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxzgLQC1RXL5LP2wsitpQbB-F0Yb3dCqGGn1UkHzLFZpedQ2kp2w");
        list.add("https://i.pinimg.com/236x/53/59/f6/5359f68cb26639996a8fb122d34fd758.jpg");
        return list.get(i);
    }
}
