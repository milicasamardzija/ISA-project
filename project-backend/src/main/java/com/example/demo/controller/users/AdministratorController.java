package com.example.demo.controller.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.users.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/administrator")
public class AdministratorController {
    private AdministratorService  administratorService;
    private AddressService addressService;

    public AdministratorController(AdministratorService administratorService,AddressService addressService) {
        this.administratorService = administratorService;
        this.addressService = addressService;
    }

    @PostMapping(value = "/addAdministrator")
    public ResponseEntity<User> addAdmin(@RequestBody UserRequest userRequest){
        Administrator a = new Administrator();
        a.setName(userRequest.getFirstname());
        a.setSurname(userRequest.getLastname());
        a.setEnabled(true);
        a.setMust_change_password(true);
        a.setEmail(userRequest.getEmail());
        a.setPassword(userRequest.getPassword());
        a.setTelephone(userRequest.getTelephone());
        a.setAddress(userRequest.getAddress());
        if (a != null){
            administratorService.save(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            System.out.print("NULL je");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
