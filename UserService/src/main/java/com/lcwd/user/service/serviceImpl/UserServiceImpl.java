package com.lcwd.user.service.serviceImpl;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.HotelService;
import com.lcwd.user.service.repository.UserRepo;
import com.lcwd.user.service.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServices.class);
    @Override
    public User saveUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=this.userRepo.findById(userId).orElseThrow( ()->new ResourceNotFoundException("user with is this id is not present on server"));
//      ArrayList ratingobject=  restTemplate.getForObject("http://localhost:8083/ratings/user/0c3e33e5-3345-48b1-93b8-fe307f180853", ArrayList.class);
//        Rating[] ratingobject=  restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), Rating[].class);
// change the host and port with service name

        Rating[] ratingobject=  restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);


        logger.info("{}",ratingobject);

        List<Rating> ratings=Arrays.stream(ratingobject).toList();

      List<Rating> ratingList = ratings.stream().map( rating ->{

            //api call to the hotel service to get the hotel
//        ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotels/"+rating.getHotelId(),Hotel.class);
//        Hotel hotel=forEntity.getBody();
          Hotel hotel=hotelService.getHotel(rating.getHotelId());
//        logger.info("response  status code :{}",forEntity.getStatusCode());

            // set hotel rating
          rating.setHotel(hotel);
            // return the rating

            return rating;
        }).collect(Collectors.toList());

      user.setRatings(ratingList);
        return user;
    }
}
