package com.example.docdelivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class RentorsController {

    @GetMapping("/ready_list")
    public ResponseEntity<?> getReadyListOfRentors(){

        return null;
    }

}


