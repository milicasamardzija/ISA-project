package com.example.demo.service.users;

import com.example.demo.model.users.BoatOwner;
import com.example.demo.repository.users.BoatOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class BoatOwnerService {

    private BoatOwnerRepository boatOwnerRepository;

    public BoatOwnerService(BoatOwnerRepository boatOwnerRepository){
        this.boatOwnerRepository = boatOwnerRepository;

    }

    public BoatOwner save(BoatOwner boatOwner){
       return this.boatOwnerRepository.save(boatOwner);
    }

    public  BoatOwner findById(int id){
       return this.boatOwnerRepository.findById(id);
    }
    public  BoatOwner findByEmail(String email){
        return this.boatOwnerRepository.findByEmail(email);
    }

    public  void remove(int id){
        boatOwnerRepository.deleteById(id);
    }
}
