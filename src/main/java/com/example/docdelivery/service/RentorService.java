package com.example.docdelivery.service;

import com.example.docdelivery.entity.ApplicationUser;
import com.example.docdelivery.entity.Rentor;
import com.example.docdelivery.exceptions.NoListException;
import com.example.docdelivery.repository.ApplicationUserRepository;
import com.example.docdelivery.repository.RentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentorService {
    private final RentorRepository rentorRepository;
    private final ApplicationUserRepository applicationUserRepository;

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

    public void toggleReady(String name) {
        ApplicationUser user = applicationUserRepository.findByUsername(name).get();
        var rentor = rentorRepository.findByUser(user).get();
        rentor.setReady(!rentor.isReady());
        rentorRepository.save(rentor);
//        if (rentor.isReady() == true) {
//            rentor.setReady(false);
//        }
//        else {rentor.setReady(true);
//
//        }
    }
}
