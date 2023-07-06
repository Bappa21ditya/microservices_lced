package com.lcwd.user.service.controller;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.services.UserServices;
//import com.netflix.discovery.util.RateLimiter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import java.util.List;
import java.util.UUID;

import static java.util.stream.DoubleStream.builder;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // create
    @Autowired
    private UserServices userServices;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    // create User
    @PostMapping("/")
    ResponseEntity<User> createUser(@RequestBody User user)
    {

        // creatig unique id
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);;
        User user1=this.userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    int retryCount=1;
    // get Users
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
//    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallBack")
    ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.info("retry count: {}",retryCount);
        retryCount++;
        User user=userServices.getUser(userId);
        return  ResponseEntity.ok(user);
    }

    //


   public ResponseEntity<User>  ratingHotelFallBack(String userId,Exception ex)
   {
//       logger.info("FallBack Method is executed because some is down",ex.getMessage());

        //= User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
      User user1=new User();
      user1.setName("Dummy");
      user1.setUserId("12345");
      user1.setAbout("This user is created dummy because some service is down");
      user1.setEmail("Dummay@gmail.com");
       return new ResponseEntity<>(user1,HttpStatus.OK);
   }
    // get All User
    @GetMapping("/")
    public  ResponseEntity<List<User>> getAllUsers()
    {
       List<User> allUser= userServices.getAllUsers();
       return ResponseEntity.ok(allUser);
    }

}
