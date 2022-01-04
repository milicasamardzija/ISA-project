package com.example.demo.controller.entities;

import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.CottageSearchDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.CottageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "api/cottages")
public class CottageController {

    private CottageService cottageService;

    public CottageController(CottageService cottageService){
        this.cottageService = cottageService;
    }

    @GetMapping
    public ResponseEntity<List<CottageDTO>> getAll(){
        List<Cottage> allCottages = cottageService.findAll();
        List<CottageDTO> cottages = new ArrayList<>();
        for(Cottage cottage : allCottages ) {
            cottages.add(new CottageDTO(cottage));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Cottage>> addUser(@RequestBody CottageSearchDTO searchParam) {

        return new ResponseEntity<>(cottageService.searchCottages(searchParam), HttpStatus.OK);
    }

}


