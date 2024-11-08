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

    private Context context;
    private NotificationManager notificationManager = null;
    private Intent intent = null;
    private Notification notification = null;
    private Notification.Builder builder = null;


    public NotificationCreator(Context context){
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }
    public void createNotification(int NOTIFICATION_ID, String CHANNEL_ID, int LARGE_ICON, int APP_ICON, String CONTENT_TEXT, Class<?> CLASS, int REQUEST_CODE, Notification.Style style, long notificationTime) {

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
                .setSubText(getElapsedTime(notificationTime))
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

    public void updateNotification(int NOTIFICATION_ID, long notificationTime) {
        if (builder != null) {
            builder.setSubText(getElapsedTime(notificationTime));
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
    public Bitmap getBitmapFromPNG(int image_id) {
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), image_id, null);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }
    public String getElapsedTime(long notificationTime){

        long currentTime = System.currentTimeMillis();
        long elapsedMillis = currentTime-notificationTime;

        long minutes = elapsedMillis / (1000*60);

        if(minutes < 1) {
            return "Just Now";

        } else if (minutes < 60) {
            return minutes+"m ago";

        }else {
            long hours = minutes / 60;

            if(hours < 24){
                return hours+"h ago";
            }else {
                long day = hours / 24;
                return day+"d ago";
            }
        }
    }

}
