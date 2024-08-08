package com.example.docdelivery.service;

import com.example.docdelivery.entity.ApplicationUser;
import com.example.docdelivery.entity.Rentor;
import com.example.docdelivery.exceptions.NoListException;
import com.example.docdelivery.repository.RentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentorService {

    @Autowired
    private RentorRepository rentorRepository;

    public List<Rentor> getReadyListOfRentors() throws NoListException {
        //TODO: проверка на null
        List<Rentor> allRentors = rentorRepository.findAll();

        if (allRentors == null) {
            throw new NoListException("List is null");
        }
        //TODO: проверка на empty
        List<Rentor> allReadyRentors = allRentors.stream().filter(Rentor::isReady).toList();

        if (allReadyRentors.isEmpty()) {
            throw new NoListException("List is empty");

        }
        return allReadyRentors;


//        return List.of(new Rentor());     todo ap remove
    }
//@Transactional
    public void createRentor(ApplicationUser user) {
        Rentor rentor = new Rentor();
        rentor.setUser(user);
        rentorRepository.save(rentor);
    }
}
