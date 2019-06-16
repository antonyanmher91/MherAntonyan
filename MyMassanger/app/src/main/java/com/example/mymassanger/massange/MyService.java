package com.example.mymassanger.massange;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.transition.Visibility;
import android.util.Log;

import com.example.mymassanger.MainActivity;
import com.example.mymassanger.R;
import com.example.mymassanger.users.UserListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.provider.FirebaseInitProvider;

import java.net.URI;

public class MyService extends Service {
    NotificationManager notificationManager;
    String string = UserListActivity.KEY;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writedatabase();
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    private void writedatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("notification").child(string);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("send")) {
                   if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    createNotificationChannel();
                    getNotificationTitle();}
                   else {
                       getNotificationTitle();
                   }
                    myRef.setValue("aaa");
                    Log.i("==p", "onDataChange: ");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    private void getNotificationTitle() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("notification").child(string + 1);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                sendNotification(value);
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }

    private void createNotificationChannel() {

        aa();

    }

    public void sendNotification(final String str) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("notification").child(string + 2);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Intent resultIntent = new Intent(getApplicationContext(), UserListActivity.class);
                resultIntent.setAction(Intent.ACTION_MAIN);
                resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
                    NotificationCompat.Builder notificationBuilder
                            = new NotificationCompat.Builder(getApplicationContext(), "CHANAL_ID").setOngoing(true);

                    notificationBuilder.setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_arrow_forward_black_24dp)
                            .setContentTitle(str)
                            .setContentIntent(resultPendingIntent)
                            .setContentText(value)
                            .setVisibility(Notification.VISIBILITY_PUBLIC)
                            .setContentInfo("Info");
                    startForeground(1, notificationBuilder.build());
                    notificationManager.notify(1, notificationBuilder.build());

                } else {
                    Notification notification = new Notification.Builder(getApplicationContext())
                            .setCategory(Notification.CATEGORY_MESSAGE)
                            .setContentTitle(str)
                            .setContentText(value)
                             .setWhen(System.currentTimeMillis())
                            .setContentIntent(resultPendingIntent)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setAutoCancel(true)
                            .setVisibility(Notification.VISIBILITY_PUBLIC).build();
                    startForeground(1, notification);

                }

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    public void aa() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                NotificationChannel channel = new NotificationChannel("CHANAL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("channel description");
                channel.enableLights(true);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                notificationManager.createNotificationChannel(channel);

            }
        }).start();


    }

}
