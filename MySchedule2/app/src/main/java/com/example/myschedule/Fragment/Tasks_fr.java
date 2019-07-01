package com.example.myschedule.Fragment;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myschedule.Adapter.TaskAdapter;
import com.example.myschedule.AlarmResiver;
import com.example.myschedule.Constanti;
import com.example.myschedule.Models.CategoryModel;
import com.example.myschedule.Models.TaskModel;
import com.example.myschedule.R;
import com.example.myschedule.SwipeController;
import com.example.myschedule.SwipeControllerActions;
import com.example.myschedule.SwipeToDeleteCallback;
import com.example.myschedule.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tasks_fr extends Fragment {
    Dialog dialog;
    @BindView(R.id.rec_view_task)
    RecyclerView recyclerView;
    TaskAdapter recyclerAdapter;
    private DatabaseHelper db;
    int color;
    ArrayList<TaskModel> list;
    FrameLayout frameLayout;
    SwipeController swipeController = null;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    public Tasks_fr() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taske_fr, container, false);
        frameLayout= view.findViewById(R.id.fr);
        FloatingActionButton fab = view.findViewById(R.id.fab_tastk);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatdialog();
            }
        });
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String name = getArguments().getString(Constanti.CategoryName);
        alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        db = Room.databaseBuilder(getContext(), DatabaseHelper.class, name)
                .allowMainThreadQueries()
                .build();
        list = new ArrayList<>();
        for (TaskModel a : db.getDataDao().getAllDataTask()) {
            if (!list.contains(a))
                list.add(a);
        }
        list = sortList(list);
        recyclerAdapter = new TaskAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//            int position = viewHolder.getAdapterPosition();
//                db.getDataDao().deletetask(list.get(position));
//            list.remove(position);
//            recyclerAdapter.notifyDataSetChanged();
//            }
//        });
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//        enableSwipeToDeleteAndUndo();

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                db.getDataDao().deletetask(list.get(position));
                list.remove(position);
                recyclerAdapter.notifyItemRemoved(position);
                recyclerAdapter.notifyItemRangeChanged(position, recyclerAdapter.getItemCount());
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftClicked(int position) {
                db.getDataDao().deletetask(list.get(position));
                list.remove(position);
                creatdialog();
                recyclerAdapter.notifyDataSetChanged();
            }
        });



        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        return view;
    }

    private void creatdialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.task_dialog);
        dialog.setCancelable(false);
        Spinner spinner = dialog.findViewById(R.id.tasc_proit);
        ImageView img = dialog.findViewById(R.id.task_img);
        final TextView txt = dialog.findViewById(R.id.tasc_txt_peior);
        final EditText name = dialog.findViewById(R.id.task_name);
        final EditText desc = dialog.findViewById(R.id.task_desc);
        Button save = dialog.findViewById(R.id.btn_task_save);
        Button canse = dialog.findViewById(R.id.btn_task_cansle);
        canse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        final EditText editText = dialog.findViewById(R.id.task_name);
        final String[] priority = {"High", "Mid", "low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, priority);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0) {
                    txt.setBackgroundColor(Color.RED);
                    color = Color.RED;
                } else if (position == 1) {
                    txt.setBackgroundColor(Color.GREEN);
                    color = Color.GREEN;
                } else {
                    txt.setBackgroundColor(Color.BLUE);
                    color = Color.BLUE;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker data = dialog.findViewById(R.id.task_data);
                TimePicker time = dialog.findViewById(R.id.task_time);
                int day = data.getDayOfMonth();
                int month = data.getMonth();
                int year = data.getYear();
                int hour = time.getCurrentHour();
                int minute = time.getCurrentMinute();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day,hour,minute);
                TaskModel taskModel = new TaskModel(name.getText().toString(),
                        desc.getText().toString(), color, String.valueOf(year + "/" + month + "/" + day), String.valueOf(hour + ":" + minute));
                db.getDataDao().insertTask(taskModel);
                list.add(taskModel);
                list = sortList(list);
                intent = new Intent(getContext(),AlarmResiver.class);
                intent.putExtra("KEY",taskModel.getDescription());
                intent.putExtra("KEY1",taskModel.getName());
                intent.putExtra("COLOR",taskModel.getColorpriority());


                long calID = 3;
                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(year, month, day, hour, minute);
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(year, month, day, hour, minute);
                endMillis = endTime.getTimeInMillis();
                ContentResolver cr = getActivity().getContentResolver();
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, startMillis);
                values.put(CalendarContract.Events.DTEND, endMillis);
                values.put(CalendarContract.Events.TITLE, taskModel.getName());
                values.put(CalendarContract.Events.DESCRIPTION, taskModel.getDescription());
                values.put(CalendarContract.Events.CALENDAR_ID, calID);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, "Armenia/Yerevan");
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);


                long eventID = 221;

                ContentResolver cr1 = getActivity().getContentResolver();
                ContentValues values1 = new ContentValues();
                values1.put(CalendarContract.Reminders.MINUTES, 15);
                values1.put(CalendarContract.Reminders.EVENT_ID, eventID);
                values1.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
                Uri uri1 = cr1.insert(CalendarContract.Reminders.CONTENT_URI, values1);

                pendingIntent= PendingIntent.getBroadcast(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000,pendingIntent); krkni
                recyclerAdapter = new TaskAdapter(list);
                recyclerView.setAdapter(recyclerAdapter);
                dialog.cancel();
            }
        });
        ;
        dialog.show();

    }

    public ArrayList<TaskModel> sortList(ArrayList<TaskModel> list) {
        int count = 0;
        ArrayList<TaskModel> arrayList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getColorpriority() == Color.RED) {
                arrayList.add(this.list.get(i));
            }
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getColorpriority() == Color.GREEN) {
                arrayList.add(this.list.get(i));
            }
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getColorpriority() == Color.BLUE) {
                arrayList.add(this.list.get(i));
            }
        }

        return arrayList;
    }
//    private void enableSwipeToDeleteAndUndo() {
//        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//
//                final int position = viewHolder.getAdapterPosition();
//
//
//                db.getDataDao().deletetask(list.get(position));
////            list.remove(position);
//            recyclerAdapter.notifyDataSetChanged();


//                Snackbar snackbar = Snackbar
//                        .make(frameLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();

//            }
//        };
//
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
//        itemTouchhelper.attachToRecyclerView(recyclerView);
    }



