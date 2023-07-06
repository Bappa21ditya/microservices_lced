package com.lcwd.hotel.serviceImpl;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.repo.HotelRepository;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcwd.hotel.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelId(String Id) {
        return hotelRepository.findById(Id).orElseThrow( ()-> new ResourceNotFoundException("hotel with this given id is not found"));
    }
}
