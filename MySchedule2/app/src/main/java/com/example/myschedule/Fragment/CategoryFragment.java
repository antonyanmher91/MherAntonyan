package com.example.myschedule.Fragment;


import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.myschedule.Adapter.CategoryAdapter;
import com.example.myschedule.CategoryActivity;
import com.example.myschedule.Models.CategoryModel;
import com.example.myschedule.R;
import com.example.myschedule.database.DatabaseHelper;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {
    Dialog dialog;
    @BindView(R.id.rec_view_catrgory)
    RecyclerView recyclerView;
    CategoryAdapter recyclerAdapter;
    private DatabaseHelper databaseHelper;
    ArrayList<CategoryModel> list;
    CategoryModel model;
    ImageView colorimg;
    View colorView;
    Bitmap bitmap;
    int color;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatdialog();
            }
        });
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        databaseHelper = CategoryActivity.getInstance().getDatabaseInstance();
        list = new ArrayList<>();

        list.add(new CategoryModel("Sport", R.drawable.ic_directions_bike_black_24dp, Color.RED));
        list.add(new CategoryModel("Daser", R.drawable.ic_import_contacts_black_24dp, Color.BLUE));
        list.add(new CategoryModel("Sport", R.drawable.ic_directions_bike_black_24dp, Color.YELLOW));
        list.add(new CategoryModel("Sport", R.drawable.ic_directions_bike_black_24dp, Color.GREEN));
        list.add(new CategoryModel("Sport", R.drawable.ic_directions_bike_black_24dp, Color.DKGRAY));
        for (CategoryModel a : databaseHelper.getDataDao().getAllData()) {
            if (!list.contains(a))
                list.add(a);
        }
        recyclerAdapter = new CategoryAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void creatdialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.category_dialog_shablonb);
        dialog.setCancelable(false);
        final EditText text = dialog.findViewById(R.id.edtxt_cadegory_dialog);
        Button save = dialog.findViewById(R.id.btn_save_category);
        Button canse = dialog.findViewById(R.id.btn_censl_category);
        colorimg = dialog.findViewById(R.id.color_img);
        colorView = dialog.findViewById(R.id.colorView);
        colorimg.setDrawingCacheEnabled(true);
        colorimg.buildDrawingCache(true);
        colorimg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN|| event.getAction()==MotionEvent.ACTION_MOVE){
                    bitmap= colorimg.getDrawingCache();
                }
                int pixsel = bitmap.getPixel((int)event.getX(),(int)event.getY());
                int red = Color.red(pixsel);
                int green = Color.green(pixsel);
                int blue = Color.blue(pixsel);
                colorView.setBackgroundColor(Color.rgb(red,green,blue));
                color=Color.rgb(red,green,blue);
                return true;
            }
        });

        dialog.show();
        canse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               model = new CategoryModel(text.getText().toString(), R.drawable.ic_fitness_center_24dp, color);

                if (stugun(model)) {
                    databaseHelper.getDataDao().insert(model);
                    list.add(model);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "arten ka", Toast.LENGTH_SHORT).show();
                }

                dialog.cancel();

            }
        });
    }
     private  boolean stugun(CategoryModel model){
         for (CategoryModel a:list) {
             if (a.getName().equals(model.getName())){
                 return false;
             }
         }
         return  true;
     }



}
