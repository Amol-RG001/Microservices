package com.amol.hotel.controllers;

import com.amol.hotel.entities.Hotel;
import com.amol.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // create
    @PostMapping()
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.create(hotel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
        return  ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    //get All Hotel
    @GetMapping("/get-all")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return  ResponseEntity.ok(hotelService.getAll());
    }




}
