package com.app.notifyapp;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.notifyapp.notification.NotificationCreator;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_CHANNEL_ID = "Message Channel";
    private static final int MESSAGE_NOTIFICATION_ID = 100;
    private static final int MESSAGE_REQUEST_CODE = 10;

    private NotificationCreator notificationCreator;
    private Handler handler;
    private long notificationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // initialize variables
        notificationTime = System.currentTimeMillis();
        notificationCreator = new NotificationCreator(getApplicationContext());
        handler = new Handler();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //


        createOrUpdateNotification();
        handler.postDelayed(updateNotificationRunnable ,60000);


    }

    private void createOrUpdateNotification(){

        String senderName = "Johan Dao";
        String content_text = "২৫০টাকার ধামাকা ডিস্কাউন্ট";
        String sub_text = "";

        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("১০০জিবি ১৫০০মি: ৮৭৪৯ (রেগুলার ৮৯৯৯)")
                .addLine("৭০জিবি ৮৪৯৮ (রেগুলার ৮৫৯৮)")
                .addLine("১৮১০মি: ৮৩৯৯ (রেগুলার ৮৫০৯)")
                .addLine("৩০ দিনের প্যাক কিনুন জলদি!!");

        notificationCreator.createNotification(
                MESSAGE_NOTIFICATION_ID,
                MESSAGE_CHANNEL_ID,
                R.drawable.message_notification_large_icon,
                R.drawable.app_icon,
                content_text,
                MainActivity.class,
                MESSAGE_REQUEST_CODE,
                inboxStyle,
                notificationTime
        );
    }
    private final Runnable updateNotificationRunnable = new Runnable() {
        @Override
        public void run() {
            notificationCreator.updateNotification(MESSAGE_NOTIFICATION_ID ,notificationTime);
            handler.postDelayed(this, 60000);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null){
            handler.removeCallbacks(updateNotificationRunnable);
        }
    }


}