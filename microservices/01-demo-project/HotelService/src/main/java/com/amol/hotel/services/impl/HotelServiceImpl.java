package com.amol.hotel.services.impl;

import com.amol.hotel.entities.Hotel;
import com.amol.hotel.exceptions.ResourceNotFoundException;
import com.amol.hotel.repositories.HotelRepository;
import com.amol.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public Hotel get(String id) {

        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found !! "+id));
    }

    /**
     * @return
     */
    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    /**
     * @param hotel
     * @return
     */
    @Override
    public Hotel
    create(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return hotelRepository.save(hotel);
    }
}
