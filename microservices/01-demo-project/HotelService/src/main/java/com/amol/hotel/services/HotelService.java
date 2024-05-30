package com.amol.hotel.services;

import com.amol.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //get single
    Hotel get(String id);

    //get all
    List<Hotel> getAll();

    // create
    Hotel create(Hotel hotel);

}
