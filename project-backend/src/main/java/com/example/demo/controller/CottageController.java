package com.example.demo.controller;

import com.example.demo.dto.CottageDTO;
import com.example.demo.model.entities.Cottage;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "api/cottages")
public class CottageController {

    @Autowired
    private CottageService cottageService;


    @GetMapping
    public ResponseEntity<List<CottageDTO>> getAll(){
        List<Cottage> allCottages = cottageService.findAll();
        List<CottageDTO> cottages = new ArrayList<>();
        for(Cottage cottage : allCottages ) {
            cottages.add(new CottageDTO(cottage));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

}


