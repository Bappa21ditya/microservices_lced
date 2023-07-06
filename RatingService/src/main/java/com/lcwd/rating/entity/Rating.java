package com.lcwd.rating.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name="ratings-table")
public class Rating {

    @Id
    private  String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
