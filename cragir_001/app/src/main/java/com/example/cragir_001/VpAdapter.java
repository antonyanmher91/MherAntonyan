package com.example.cragir_001;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class VpAdapter extends PagerAdapter {
    private Context context;
    Button button;
    private List<Auto> autoList;
    private LayoutInflater layoutInflater;
    public VpAdapter(Context context, List<Auto> autoList){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.autoList = autoList;
    }
    @Override
    public int getCount() {
        return autoList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final View view = this.layoutInflater.inflate(R.layout.auto_list, container, false);
        ImageView displayImage = view.findViewById(R.id.large_image);
        TextView imageText = view.findViewById(R.id.image_name);


        displayImage.setImageResource(this.autoList.get(position).getImageId());
        imageText.setText(this.autoList.get(position).getImageName());
        container.addView(view);
        button = container.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                Log.i("===p", "onClick: ");

                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}