package com.example.demo.controller;


import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.service.CottageOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {
    @Autowired
    private CottageOwnerService cottageOwnerService;


    /*
     * setter based dependency injection
      @Autowired
    public void setCottageOwnerService(CottageOwnerService ownerService) {
        this.cottageOwnerService = ownerService;
    }*/


    @GetMapping
    public ResponseEntity<List<CottageDTO.CottageOwnerDTO>> getAll(){
        List<CottageOwner> allOwners = cottageOwnerService.findAll();
        List<CottageDTO.CottageOwnerDTO> cottages = new ArrayList<>();
        for(CottageOwner owner : allOwners ) {
           cottages.add(new CottageDTO.CottageOwnerDTO(owner));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

}
