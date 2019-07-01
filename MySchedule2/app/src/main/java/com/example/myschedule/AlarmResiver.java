package com.example.myschedule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmResiver extends BroadcastReceiver {
    Context context;
    NotificationManager notificationManager;
    String description;
    String name;
    int color;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("==p", "lalalalalalala: ");
        this.context = context;
        description = intent.getStringExtra("KEY");
        name = intent.getStringExtra("KEY1");
        color = intent.getIntExtra("COLOR", Color.RED);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannelGroup(
                    new NotificationChannelGroup("GRUP_ID", "MySheduler"));
            NotificationChannel channel = new NotificationChannel("CHANAL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channel description");
            channel.enableLights(true);
            channel.setGroup("GRUP_ID");
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
             notificationManager.createNotificationChannel(channel);
        }
        sendNotification();
    }

    public void sendNotification() {
      int grup  =0;
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setAction(Intent.ACTION_MAIN);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder notificationBuilder
                    = new NotificationCompat.Builder(context, "CHANAL_ID");
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setColor(color)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                    .setContentTitle(name)
                    . setGroup ( "GRUP_ID" )
                    .setGroupSummary(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                    .setContentIntent(resultPendingIntent)
                    .setContentText(description)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setContentInfo("Info");
            notificationManager.notify(1, notificationBuilder.build());

        } else {
            Notification.Builder notification = new Notification.Builder(context)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setContentTitle(name)
                    .setGroup("GRUP_ID")
                     .setGroupSummary(true)
                    .setContentText(description)
                    .setWhen(System.currentTimeMillis())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                    .setContentIntent(resultPendingIntent)
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                    .setAutoCancel(true)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.notify(1, notification.build());


        }

    }


}

