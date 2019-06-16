package com.example.cragir_001.pakage2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cragir_001.R;

import java.util.List;

public class RecycViewAdapterMassange  extends RecyclerView.Adapter<RecycViewAdapterMassange.ViewHolder>{
    Context massange;
    List<Massange> mData;

    public RecycViewAdapterMassange(Context massange, List<Massange> mData) {
        this.massange = massange;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(massange).inflate(R.layout.main3_item_massange, viewGroup, false);
        RecycViewAdapterMassange.ViewHolder myViewHolder = new RecycViewAdapterMassange.ViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(mData.get(i).getName());
        viewHolder.massange.setText(mData.get(i).getMassage());
        viewHolder.img.setImageResource(mData.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        ImageView send;
        TextView name;
        TextView massange;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgMassange);
            send=itemView.findViewById(R.id.btn_massange);
            name=itemView.findViewById(R.id.txtname_massange);
            massange=itemView.findViewById(R.id.txtmaassange_massange);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str= mData.get(getAdapterPosition()).getPhonenumber();
                    Uri uri = Uri.parse(("smsto:"+str));
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "");
                    v.getContext().startActivity(intent);

                }
            });

        }
    }
}
