package com.example.docdelivery.service;

import com.example.docdelivery.entity.Rentor;
import com.example.docdelivery.repository.RentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentorService {

    @Autowired
    private RentorRepository rentorRepository;

    public List<Rentor> getReadyListOfRentors() {
//TODO: проверка на null
        List<Rentor> allRentors = (List<Rentor>) rentorRepository.findAll();
        //TODO: проверка на empty
        List<Rentor> allReadyRentors = allRentors.stream().filter(Rentor::isReady).toList();
        return allReadyRentors;
    }

}
