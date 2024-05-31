package com.amol.user.service.services.impl;

import com.amol.user.service.entities.Hotel;
import com.amol.user.service.entities.Rating;
import com.amol.user.service.entities.User;
import com.amol.user.service.exceptions.ResourceNotFoundException;
import com.amol.user.service.repositories.UserRepository;
import com.amol.user.service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Rating[] ratingOfUserData = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        // logger.info() is used for audit purpose.
        logger.info("{} " ,ratingOfUserData);

        // convert array to ArrayList
        List<Rating> ratings =  Arrays.stream(ratingOfUserData).toList();


        List<Rating> ratingList = ratings.stream().map( rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/hotels/33a5b6dd-a557-4da7-9ad0-bf1325861246
            ResponseEntity<Hotel> forEntity  = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Response status code: {} ", forEntity.getStatusCode());
            // set the hotel to rating
            rating.setHotel(hotel);
            //return rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
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
