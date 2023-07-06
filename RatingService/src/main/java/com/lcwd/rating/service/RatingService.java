package com.lcwd.rating.service;
import com.lcwd.rating.entity.Rating;
import java.util.List;
public interface RatingService {

    // create
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    // get All by userId
    List<Rating> getRatingUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
