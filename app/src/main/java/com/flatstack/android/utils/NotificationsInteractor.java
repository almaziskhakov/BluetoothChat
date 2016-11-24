package com.flatstack.android.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.flatstack.android.R;
import com.flatstack.android.main_screen.MainActivity;

/**
 * Created by klim-mobile on 24.11.2016.
 */

public class NotificationsInteractor {

    private Context context;
    private NotificationManager manager;

    public NotificationsInteractor(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
    }

    public void senNotification(String message) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("You have a new message")
                .setContentText(message)
                .setNumber(0)
                .setSmallIcon(R.drawable.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent).build();

        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);
    }
}
