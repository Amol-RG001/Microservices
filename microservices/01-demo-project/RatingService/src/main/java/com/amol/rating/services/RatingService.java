package com.amol.rating.services;

import com.amol.rating.entities.Rating;
import java.util.List;


public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by userId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel

    List<Rating> getRatingByHotelId(String hotelId);
}
