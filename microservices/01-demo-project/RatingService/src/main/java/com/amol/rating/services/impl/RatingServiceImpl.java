package com.amol.rating.services.impl;

import com.amol.rating.entities.Rating;
import com.amol.rating.repositories.RatingRepository;
import com.amol.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    /**
     * @param rating
     * @return
     */
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * @return
     */
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
