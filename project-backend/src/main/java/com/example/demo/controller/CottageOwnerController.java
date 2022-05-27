package com.example.demo.controller;


import com.example.demo.dto.entities.CottageOwnerDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.entities.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {


    private CottageOwnerService cottageOwnerService;

    public CottageOwnerController(CottageOwnerService cottageOwnerService) {
        this.cottageOwnerService = cottageOwnerService;
    }

    @GetMapping
    public ResponseEntity<List<CottageOwnerDTO>> getAll(){
        List<CottageOwner> allOwners = cottageOwnerService.findAll();
        List<CottageOwnerDTO> cottages = new ArrayList<>();
        for(CottageOwner owner : allOwners ) {
           cottages.add(new CottageOwnerDTO(owner));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

    @GetMapping("/cottageOwnerUser/{id}")
    public  ResponseEntity<UserDTO> fetchCottageOwnerByCottage(@PathVariable  int id){
        User user = this.cottageOwnerService.fetchCottageOwnerByCottage(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
}