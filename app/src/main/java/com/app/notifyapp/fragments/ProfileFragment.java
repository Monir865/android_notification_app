package com.app.notifyapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.notifyapp.R;
import com.app.notifyapp.dao.UserDAO;
import com.app.notifyapp.model.User;

public class ProfileFragment extends Fragment {

    private ImageView ProfileProfilePhoto;
    private TextView ProfileStatus, ProfileFullName, ProfileEmail, ProfileContact, ProfileBio;
    private AppCompatButton ProfileAddUpdateDialogBtn;
    private UserDAO userDAO;
    private int userID = 0;
    private static final String ARG1 = "UserID";



    public ProfileFragment() {
        userDAO = new UserDAO();
    }

    public static ProfileFragment getInstance(int user_id){
        ProfileFragment profileFragment = new ProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARG1, user_id);
        profileFragment.setArguments(bundle);

        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileProfilePhoto = view.findViewById(R.id.profile_profile_photo);
        ProfileStatus = view.findViewById(R.id.profile_status);
        ProfileFullName = view.findViewById(R.id.profile_full_name);
        ProfileEmail = view.findViewById(R.id.profile_email);
        ProfileContact = view.findViewById(R.id.profile_contact);
        ProfileBio = view.findViewById(R.id.profile_bio);
        ProfileAddUpdateDialogBtn = view.findViewById(R.id.profile_add_update_btn);

        if (getArguments() != null) {
            userID = getArguments().getInt(ARG1);
            User user = userDAO.getUser(userID);
            setUserData(user);
            Toast.makeText(view.getContext(), "User-Id : " + userID, Toast.LENGTH_SHORT).show();
        }
        ProfileAddUpdateDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProfileAddUpdateContainer(view.getContext());
            }
        });

        return view;
    }

    private void loadProfileAddUpdateContainer(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.profile_add_update_dialog_container);

        EditText ProfileFirstNameET = dialog.findViewById(R.id.PAU_firstName);
        EditText ProfileLastNameET = dialog.findViewById(R.id.PAU_lastName);
        EditText ProfileEmailET = dialog.findViewById(R.id.PAU_email);
        EditText ProfileContactET = dialog.findViewById(R.id.PAU_contactNumber);
        EditText ProfileBioET = dialog.findViewById(R.id.PAU_bio);
        Spinner ProfileSpinnerStatus = dialog.findViewById(R.id.PAU_spinnerStatus);
        AppCompatButton ProfileAddUpdateBtn = dialog.findViewById(R.id.PAU_addupdateButton);

        User user = userDAO.getUser(userID);

        ProfileFirstNameET.setText(user.getFirstName());
        ProfileLastNameET.setText(user.getLastName());
        ProfileEmailET.setText(user.getEmail());
        ProfileContactET.setText(user.getPhoneNumber());
        ProfileBioET.setText(user.getBio());

        // Set up the spinner data (Active, Hold, Deactive)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"Active", "On Hold", "De-active"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ProfileSpinnerStatus.setAdapter(adapter);

        ProfileAddUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = ProfileFirstNameET.getText().toString().trim();
                String lastName = ProfileLastNameET.getText().toString().trim();
                String email = ProfileEmailET.getText().toString().trim();
                String contactNumber = ProfileContactET.getText().toString().trim();
                String bio = ProfileBioET.getText().toString().trim();
                String status = ProfileSpinnerStatus.getSelectedItem().toString();

                User updatedUser = new User();
                updatedUser.setFirstName(firstName);
                updatedUser.setLastName(lastName);
                updatedUser.setEmail(email);
                updatedUser.setPhoneNumber(contactNumber);
                updatedUser.setBio(bio);
                updatedUser.setStatus(status);

                userDAO.updateUser(userID, updatedUser);

                setUserData(updatedUser);

                Toast.makeText(context, "User data updated", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void setUserData(User user) {
        // Set user data to the profile TextViews
        ProfileFullName.setText(user.getFirstName() + " " + user.getLastName());
        ProfileEmail.setText(user.getEmail());
        ProfileContact.setText(user.getPhoneNumber());
        ProfileBio.setText(user.getBio());
        ProfileStatus.setText(user.getStatus());
    }
}
