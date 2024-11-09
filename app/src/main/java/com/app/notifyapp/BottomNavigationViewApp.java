package com.app.notifyapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.notifyapp.fragments.AccountFragment;
import com.app.notifyapp.fragments.ChatFragment;
import com.app.notifyapp.fragments.HomeFragment;
import com.app.notifyapp.fragments.ProfileFragment;
import com.app.notifyapp.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewApp extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view_app);

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set FrameLayout height dynamically
        bottomNavigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bottomNavigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int currentHeightOfBNV = bottomNavigationView.getHeight();

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
                params.setMargins(0,0,0,currentHeightOfBNV);
                frameLayout.setLayoutParams(params);
            }
        });
        // Set click listener on Bottom Navigation Menu Items
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.bot_nav_app_menu_home) {
                    loadFragment(new HomeFragment(), true);
                } else if (id == R.id.bot_nav_app_menu_chat) {
                    loadFragment(new ChatFragment(), false);
                } else if (id == R.id.bot_nav_app_menu_account) {
                    loadFragment(new AccountFragment(), false);
                } else if (id == R.id.bot_nav_app_menu_profile) {
                    loadFragment(new ProfileFragment(), false);
                } else {
                    loadFragment(new SettingFragment(), false);
                }

                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.bot_nav_app_menu_home);









    }

    private void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag) {
            ft.add(R.id.frame_layout, fragment);
        } else {
            ft.replace(R.id.frame_layout, fragment);
        }
        ft.commit();
    }


}



