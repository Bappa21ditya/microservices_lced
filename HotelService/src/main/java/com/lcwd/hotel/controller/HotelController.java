package com.lcwd.hotel.controller;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/hotels/")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // create
    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        String randomUserId= UUID.randomUUID().toString();
        hotel.setId(randomUserId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    // get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId)
    {
     return    ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelId(hotelId));
    }
    // get all
    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        return  ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());
    }

}
