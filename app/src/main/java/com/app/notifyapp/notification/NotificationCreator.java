package com.app.notifyapp.notification;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;

import com.app.notifyapp.R;

public class NotificationCreator {

    Context context;
    NotificationManager notificationManager = null;

    public NotificationCreator(Context context){
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }


    public void createNotification(int NOTIFICATION_ID, String CHANNEL_ID, int LARGE_ICON, int APP_ICON, String CONTENT_TEXT, String SUB_TEXT, Class<?> CLASS, int REQUEST_CODE, Notification.Style style) {
        Intent intent = null;
        Notification notification = null;
        Notification.Builder builder = null;


        intent = new Intent(context, CLASS);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        int pendingIntentFlags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                                    ?PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
                                    :PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, intent, pendingIntentFlags);


        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Message Channel", NotificationManager.IMPORTANCE_HIGH));
            builder = new Notification.Builder(context, CHANNEL_ID);
        }else{
            builder = new Notification.Builder(context);
        }

        builder.setLargeIcon(getBitmapFromPNG(LARGE_ICON))
                .setSmallIcon(APP_ICON)
                .setSubText(SUB_TEXT)
                .setContentText(CONTENT_TEXT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        if(style != null){
            if(style instanceof Notification.BigPictureStyle){
                builder.setStyle((Notification.BigPictureStyle) style);
            } else if (style instanceof Notification.InboxStyle) {
                builder.setStyle((Notification.InboxStyle) style);
            }
        }

        notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    public void createNotification(int NOTIFICATION_ID, String CHANNEL_ID, int LARGE_ICON, int APP_ICON, String CONTENT_TEXT, String SUB_TEXT, Class<?> CLASS, int REQUEST_CODE){
        Notification notification = null;

        Intent intent = new Intent(context, CLASS);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int pendingIntentFlags = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S
                                    ?PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
                                    :PendingIntent.FLAG_UPDATE_CURRENT;

        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE,intent, pendingIntentFlags);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "MESSAGE NOTIFICATION", NotificationManager.IMPORTANCE_HIGH));

            notification = new Notification.Builder(context, CHANNEL_ID)
                    .setLargeIcon(getBitmapFromPNG(LARGE_ICON))
                    .setSmallIcon(APP_ICON)
                    .setContentText(CONTENT_TEXT)
                    .setSubText(SUB_TEXT)
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        }else{
            notification = new Notification.Builder(context)
                    .setLargeIcon(getBitmapFromPNG(LARGE_ICON))
                    .setSmallIcon(APP_ICON)
                    .setContentText(CONTENT_TEXT)
                    .setSubText(SUB_TEXT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);

    }
    public void createNotification(int NOTIFICATION_ID, String CHANNEL_ID, int LARGE_ICON, int APP_ICON, String CONTENT_TEXT, String SUB_TEXT){


        Notification notification = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "MESSAGE NOTIFICATION", NotificationManager.IMPORTANCE_HIGH));

            notification = new Notification.Builder(context)
                    .setLargeIcon(getBitmapFromPNG(LARGE_ICON))
                    .setSmallIcon(APP_ICON)
                    .setContentText(CONTENT_TEXT)
                    .setSubText(SUB_TEXT)
                    .setChannelId(CHANNEL_ID)
                    .build();
        }else{
            notification = new Notification.Builder(context)
                    .setLargeIcon(getBitmapFromPNG(R.drawable.message_notification_large_icon))
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentText(CONTENT_TEXT)
                    .setSubText(SUB_TEXT)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);

    }
    public Bitmap getBitmapFromPNG(int image_id) {
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), image_id, null);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }


}
