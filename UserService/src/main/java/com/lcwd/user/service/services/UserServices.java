package com.lcwd.user.service.services;

import com.lcwd.user.service.entity.User;

import java.util.List;


public interface UserServices {
    // create new User
    User saveUser(User user);

    // get all user
    List<User> getAllUsers();

    // get a single user
    User getUser(String userId);

    // TODO
    // delete//update
}
