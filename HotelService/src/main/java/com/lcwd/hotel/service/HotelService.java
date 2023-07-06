package com.lcwd.hotel.service;

import com.lcwd.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotelId(String Id);
}
