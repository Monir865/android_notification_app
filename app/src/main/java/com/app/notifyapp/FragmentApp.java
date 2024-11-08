package com.app.notifyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.notifyapp.fragments.ChatFragment;
import com.app.notifyapp.fragments.MoreFragment;
import com.app.notifyapp.fragments.NotificationFragment;

public class FragmentApp extends AppCompatActivity {

    private FrameLayout FrameContainer;
    private AppCompatButton ChatButton, NotificationButton, MoreButton;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment_app);

        //
        FrameContainer = findViewById(R.id.frame_container);
        ChatButton = findViewById(R.id.chat_button);
        NotificationButton = findViewById(R.id.notification_button);
        MoreButton = findViewById(R.id.more_button);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //
        loadFragment( new ChatFragment(), 0);

        ChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment( new ChatFragment(), 1);
            }
        });

        NotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment( new NotificationFragment(), 1);
            }
        });

        MoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment( new MoreFragment(), 1);
            }
        });


    }

    private void loadFragment(Fragment fragment, int flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag == 0) {
            ft.add(R.id.frame_container, fragment);
        } else if (flag == 1) {
            ft.replace(R.id.frame_container, fragment);
        }
        ft.commit();

    }



}