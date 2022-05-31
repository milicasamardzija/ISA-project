package com.example.demo.service.users;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.BoatOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class BoatOwnerService {

    private BoatOwnerRepository boatOwnerRepository;
    private UserService userService;

    public BoatOwnerService(BoatOwnerRepository boatOwnerRepository, UserService userService){
        this.boatOwnerRepository = boatOwnerRepository;
        this.userService = userService;
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

    public User fetchBoatOwnerByBoat(int idBoat){
        Boat boat = this.boatOwnerRepository.fetchBoatOwnerByBoat(idBoat);
        return this.userService.findByEmail(boat.getBoatOwner().getEmail());
    }
    public BoatOwner getOwnerWithBoats(int id) {
        return boatOwnerRepository.findOwnerWithBoats(id);
}


}
