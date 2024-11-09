package com.app.notifyapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.app.notifyapp.adapters.ViewPagerAppTabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutApp extends AppCompatActivity {
    private com.google.android.material.tabs.TabLayout TabLayout;
    private androidx.viewpager.widget.ViewPager ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tab_layout_app);

        //
        TabLayout = findViewById(R.id.tab_layout);
        ViewPager = findViewById(R.id.view_pager);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //
        ViewPagerAppTabLayoutAdapter adapter = new ViewPagerAppTabLayoutAdapter(getSupportFragmentManager());
        ViewPager.setAdapter(adapter);
        TabLayout.setupWithViewPager(ViewPager);


    }



}