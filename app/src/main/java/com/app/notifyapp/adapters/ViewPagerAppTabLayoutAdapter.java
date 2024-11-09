package com.app.notifyapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.notifyapp.fragments.ChatFragment;
import com.app.notifyapp.fragments.MoreFragment;
import com.app.notifyapp.fragments.NotificationFragment;

public class ViewPagerAppTabLayoutAdapter extends FragmentPagerAdapter{


    public ViewPagerAppTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new ChatFragment();
        }else if(position == 1){
            return new NotificationFragment();
        }else {
            return new MoreFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Chat";
        }else if(position == 1){
            return "Notification";
        }else {
            return "More";
        }
    }

}
