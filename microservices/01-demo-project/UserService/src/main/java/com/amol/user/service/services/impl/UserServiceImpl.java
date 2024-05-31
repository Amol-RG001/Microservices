package com.amol.user.service.services.impl;

import com.amol.user.service.entities.User;
import com.amol.user.service.exceptions.ResourceNotFoundException;
import com.amol.user.service.repositories.UserRepository;
import com.amol.user.service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepo;

//    we have multiple way to call
//    HTTP API from microservice like RestTemplate, Feign Client, etc.

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {

        //GENERATE RANDOM USER ID
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepo.save(user);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUser(String userId) {
        User user =  userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server!!"+userId));

        // fetch rating of the above user from RATING SERVICE
        // http://localhost:8083/ratings/user/66e07070-dd84-4071-a21e-9b8c7126676f

        ArrayList ratingOfUserData = restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), ArrayList.class);

        // logger.info() is used for audit purpose.
        logger.info("{} " ,ratingOfUserData);
        user.setRatings(ratingOfUserData);
        return user;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public void updateUser(String userId, User user) {
        User user1 = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("UserId : "+userId));

        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setAbout(user.getAbout());
        userRepo.save(user1);
    }

    /**
     * @param userId
     * @return
     */

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}
