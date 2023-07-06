package com.lcwd.user.service.entity;

import lombok.Data;

@Data
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;
    private Hotel hotel;
}
