package com.amol.user.service.services.impl;

import com.amol.user.service.entities.User;
import com.amol.user.service.exceptions.ResourceNotFoundException;
import com.amol.user.service.repositories.UserRepository;
import com.amol.user.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;

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
        return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server!!"+userId));
    }



    /**
     * @param userId
     * @return
     */
    @Override
    public void updateUser(String userId, User user) {
        User user1 = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("UserId : "+userId));
        //CODE NOT YET COMPLETE
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
