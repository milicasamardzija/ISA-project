package com.example.demo.service.users;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.BoatOwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class BoatOwnerService {

    private BoatOwnerRepository boatOwnerRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public BoatOwnerService(PasswordEncoder passwordEncoder, BoatOwnerRepository boatOwnerRepository, UserService userService){
        this.boatOwnerRepository = boatOwnerRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public BoatOwner save(User boatOwner){
        BoatOwner owner= new BoatOwner();
        owner.setName(boatOwner.getName());
        owner.setSurname(boatOwner.getSurname());
        owner.setAddress(boatOwner.getAddress());
        owner.setBoatList(new ArrayList<>()); //inicijalno prazna
//        owner.setPassword(passwordEncoder.encode(boatOwner.getPassword()));
        owner.setPassword(boatOwner.getPassword());
        owner.setEmail(boatOwner.getEmail());
        owner.setTelephone(boatOwner.getTelephone());
        owner.setRole(boatOwner.getRole());
        owner.setEnabled(true);
        owner.setMust_change_password(false); //Cemu?
        owner.setLastPasswordResetDate(null); //???
        owner.setReasonForRegistration(boatOwner.getReasonForRegistration());

       return this.boatOwnerRepository.save(owner);
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
