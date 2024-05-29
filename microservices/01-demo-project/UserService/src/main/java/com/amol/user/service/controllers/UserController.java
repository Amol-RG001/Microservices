package com.amol.user.service.controllers;

import com.amol.user.service.entities.User;
import com.amol.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User getUser = userService.getUser(userId);
        return ResponseEntity.ok(getUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> getAll = userService.getAllUser();
        return ResponseEntity.ok(getAll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>upToDateBookById(@PathVariable("id") String id, @RequestBody User user){
        try{
            userService.updateUser(id, user);
            return new ResponseEntity<>("User updated with id "+id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String id){
        try{
            userService.deleteUser(id);
            return new  ResponseEntity<> ("User deleted with id "+id, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
