package com.example.docdelivery.controller;

import com.example.docdelivery.entity.Rentor;
import com.example.docdelivery.exceptions.NoListException;
import com.example.docdelivery.service.RentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentorsController {

    @Autowired
    RentorService rentorService;

    @GetMapping("/ready_list")
    public String getReadyListOfRentors(Model model) {
        try {
            List<Rentor> readyRentors = rentorService.getReadyListOfRentors();
            model.addAttribute("ready_rentor_list", readyRentors);
            return "ready_rentors";
        } catch (NoListException e) {
            return "request_form";          // todo ap create request_form.html
        }
    }


}


