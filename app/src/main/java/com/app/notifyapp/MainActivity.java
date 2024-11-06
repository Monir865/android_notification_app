package com.app.notifyapp;

import android.app.Notification;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //


        String senderName = "Johan Dao";
        String content_text = "New Message";
        String sub_text = "Message sent from "+senderName;

        NotificationCreator notificationCreator = new NotificationCreator(getApplicationContext());
        //notificationCreator.createNotification(MESSAGE_NOTIFICATION_ID, MESSAGE_CHANNEL_ID, R.drawable.message_notification_large_icon, R.drawable.app_icon, content_text, sub_text, MainActivity.class, MESSAGE_REQUEST_CODE);

        //
        // now call our new method from here
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(notificationCreator.getBitmapFromPNG(R.drawable.message_notification_big_picture))
                .setBigContentTitle("Please check message. It can be important!")
                .setSummaryText(sub_text);

        // Call the createNotification method
        notificationCreator.createNotification(
                MESSAGE_NOTIFICATION_ID,
                MESSAGE_CHANNEL_ID,
                R.drawable.message_notification_large_icon,
                R.drawable.app_icon,
                content_text,
                sub_text,
                MainActivity.class,
                MESSAGE_REQUEST_CODE,
                bigPictureStyle
        );

    }



}