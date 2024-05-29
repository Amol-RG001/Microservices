package com.amol.user.service.services;

import com.amol.user.service.entities.User;

import java.lang.annotation.Documented;
import java.util.List;

public interface UserService {


   // user operations

    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUser();

    //get user by id
    User getUser(String userId);

    //update user by id
    void updateUser(String userId, User user);

    //delete user by id
     void deleteUser(String userId);
}
