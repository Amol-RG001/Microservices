package com.amol.rating.repositories;

import com.amol.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom finder methods
    List<Rating> findByHotelId(String hotelId);
    List<Rating> findByUserId(String userId);
}
