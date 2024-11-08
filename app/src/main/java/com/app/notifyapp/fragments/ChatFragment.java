package com.app.notifyapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.notifyapp.R;

public class ChatFragment extends Fragment {

    public ChatFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        TextView titleText = view.findViewById(R.id.bottom_fragment_title);
        titleText.setText("We Updated Title From OnCreateView Method!");




        return view;
    }


}