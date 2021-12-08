package com.example.demo.controller;

import com.example.demo.dto.BoatDTO;
import com.example.demo.model.entities.Boat;
import com.example.demo.service.BoatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/boats")
public class BoatController {

    private BoatService boatService;

    public BoatController(BoatService boatService){
        this.boatService = boatService;
    }

    @GetMapping
    public ResponseEntity<List<BoatDTO>> getAll(){
        List<Boat> allBoats = boatService.findAll();
        List<BoatDTO> cottages = new ArrayList<>();
        for(Boat boat : allBoats ) {
            cottages.add(new BoatDTO(boat));
        }
        return  new ResponseEntity<>(cottages, HttpStatus.OK);
    }

}
