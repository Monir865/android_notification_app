package com.app.notifyapp.dao;

import com.app.notifyapp.R;
import com.app.notifyapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
        init();
    }

    private void init() {
        users.add(new User(1, "John", "Doe", "john.doe@example.com", "123 Maple Street, New York, NY", "1234567890", R.drawable.default_profile_picture, "Loves hiking and outdoor adventures.", "Active"));
        users.add(new User(2, "Jane", "Smith", "jane.smith@example.com", "456 Oak Avenue, Los Angeles, CA", "2345678901", R.drawable.default_profile_picture, "Passionate about technology and innovation.", "Active"));
        users.add(new User(3, "Michael", "Johnson", "michael.johnson@example.com", "789 Pine Road, Chicago, IL", "3456789012", R.drawable.default_profile_picture, "Enjoys playing basketball.", "Active"));
        users.add(new User(4, "Emily", "Davis", "emily.davis@example.com", "101 Cedar Street, Houston, TX", "4567890123", R.drawable.default_profile_picture, "Avid reader and writer.", "Active"));
        users.add(new User(5, "Chris", "Wilson", "chris.wilson@example.com", "202 Spruce Avenue, Phoenix, AZ", "5678901234", R.drawable.default_profile_picture, "Loves to travel and explore new places.", "Active"));
        users.add(new User(6, "Jessica", "Lee", "jessica.lee@example.com", "303 Birch Road, Philadelphia, PA", "6789012345", R.drawable.default_profile_picture, "Yoga and wellness enthusiast.", "Active"));
        users.add(new User(7, "David", "Martinez", "david.martinez@example.com", "404 Ash Street, San Antonio, TX", "7890123456", R.drawable.default_profile_picture, "Tech enthusiast and gamer.", "Active"));
        users.add(new User(8, "Sarah", "Anderson", "sarah.anderson@example.com", "505 Elm Avenue, San Diego, CA", "8901234567", R.drawable.default_profile_picture, "Enjoys photography and art.", "Active"));
        users.add(new User(9, "Daniel", "Thomas", "daniel.thomas@example.com", "606 Willow Road, Dallas, TX", "9012345678", R.drawable.default_profile_picture, "Cycling and fitness enthusiast.", "Active"));
        users.add(new User(10, "Laura", "Jackson", "laura.jackson@example.com", "707 Redwood Street, San Jose, CA", "0123456789", R.drawable.default_profile_picture, "Foodie and chef-in-training.", "Active"));
        users.add(new User(11, "James", "White", "james.white@example.com", "808 Alder Lane, Austin, TX", "2345678901", R.drawable.default_profile_picture, "Podcast host and content creator.", "Active"));
        users.add(new User(12, "Ashley", "Harris", "ashley.harris@example.com", "909 Fir Avenue, Jacksonville, FL", "3456789012", R.drawable.default_profile_picture, "Dog lover and animal rights advocate.", "Active"));
        users.add(new User(13, "Joshua", "Clark", "joshua.clark@example.com", "1010 Cypress Road, San Francisco, CA", "4567890123", R.drawable.default_profile_picture, "Environmentalist and outdoorsman.", "Active"));
        users.add(new User(14, "Brittany", "Lewis", "brittany.lewis@example.com", "1111 Maple Street, Columbus, OH", "5678901234", R.drawable.default_profile_picture, "Interior designer and blogger.", "Active"));
        users.add(new User(15, "Andrew", "Walker", "andrew.walker@example.com", "1212 Oak Avenue, Indianapolis, IN", "6789012345", R.drawable.default_profile_picture, "Loves classic cars and mechanics.", "Active"));
        users.add(new User(16, "Rachel", "Hall", "rachel.hall@example.com", "1313 Pine Road, Charlotte, NC", "7890123456", R.drawable.default_profile_picture, "Runs a small bakery business.", "Active"));
        users.add(new User(17, "Ryan", "Allen", "ryan.allen@example.com", "1414 Cedar Street, Seattle, WA", "8901234567", R.drawable.default_profile_picture, "Hiking and mountain climbing enthusiast.", "Active"));
        users.add(new User(18, "Stephanie", "Young", "stephanie.young@example.com", "1515 Spruce Avenue, Denver, CO", "9012345678", R.drawable.default_profile_picture, "Fashion and lifestyle influencer.", "Active"));
        users.add(new User(19, "Brandon", "King", "brandon.king@example.com", "1616 Birch Road, Washington, DC", "0123456789", R.drawable.default_profile_picture, "Photography hobbyist.", "Active"));
        users.add(new User(20, "Samantha", "Wright", "samantha.wright@example.com", "1717 Ash Street, Nashville, TN", "2345678901", R.drawable.default_profile_picture, "Music lover and guitar player.", "Active"));
    }


    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public boolean removeUser(int userId) {
        return users.removeIf(user -> user.getId() == userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

}
