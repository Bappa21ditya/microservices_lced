package com.lcwd.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping("/")
    public ResponseEntity<List<String>> getStaffs()
    {
        List<String> l1 =Arrays.asList("Michel","jhon","leman");
        return new ResponseEntity<>(l1, HttpStatus.OK);
    }

}
