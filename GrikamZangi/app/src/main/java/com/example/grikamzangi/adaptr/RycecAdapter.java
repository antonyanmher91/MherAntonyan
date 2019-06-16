package com.example.grikamzangi.adaptr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grikamzangi.R;
import com.example.grikamzangi.User.User;

import java.util.List;
import java.util.zip.Inflater;

import static android.support.v4.content.ContextCompat.startActivity;

public class RycecAdapter extends RecyclerView.Adapter<RycecAdapter.ViewHolder> {
    List<User> list;

    public RycecAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RycecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_pow,viewGroup,false);
        ViewHolder rycecAdapter = new ViewHolder(view);
        return  rycecAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull RycecAdapter.ViewHolder viewHolder, int i) {
        User user = list.get(i);
        viewHolder.name.setText(user.getName()+" ");
        viewHolder.surName.setText(user.getSurName()+" ");
        viewHolder.phoneNumber.setText(user.getNumber()+" ");
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView surName;
        TextView phoneNumber;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.user_name);
            surName = itemView.findViewById(R.id.user_surName);
            phoneNumber= itemView.findViewById(R.id.user_phoneNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    EditText editText= itemView.findViewById(R.id.Edsms);
                    Uri uri = Uri.parse("smsto:095352505");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                    String str = String.valueOf(editText.getText());
                    intent.putExtra("sms_body", "456465");
                    v.getContext().startActivity(intent);
                  notifyDataSetChanged();

                }

            });

        }


    }
}
