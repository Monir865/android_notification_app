package com.app.notifyapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_CHANNEL_ID = "Message Channel";
    private static final int MESSAGE_NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Message Sender Data
        String senderName = "Johan Dao";

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(new NotificationChannel(MESSAGE_CHANNEL_ID, "Message Channel", NotificationManager.IMPORTANCE_HIGH));

            notification = new Notification.Builder(getApplicationContext(), MESSAGE_CHANNEL_ID)
                    .setLargeIcon(getBitmapFromPNG(R.drawable.message_notification_large_icon))
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentText("New Message")
                    .setSubText("Message from " + senderName)
                    .build();

        } else {
            notification = new Notification.Builder(getApplicationContext())
                    .setLargeIcon(getBitmapFromPNG(R.drawable.message_notification_large_icon))
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentText("New Message")
                    .setSubText("Message from " + senderName)
                    .build();
        }

        nm.notify(MESSAGE_NOTIFICATION_ID, notification);
    }

    private Bitmap getBitmapFromPNG(int image_id) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), image_id, null);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }


}