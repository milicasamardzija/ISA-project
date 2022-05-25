package com.example.demo.controller.entities;

import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Cottage;
import com.example.demo.service.entities.CottageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Cottage>> search(@RequestBody SearchDTO searchParam) {
        return new ResponseEntity<>(cottageService.searchCottages(searchParam), HttpStatus.OK);
    }

    @GetMapping("/myCottages/{id}")
    public ResponseEntity<List<CottageDTO>> getOwnersCottages(@PathVariable int id){
        List<Cottage> allOwnerCottages = cottageService.findAllOwnerCottages(id);
        List<CottageDTO> ret = new ArrayList<>();
        for(Cottage c : allOwnerCottages){
            ret.add(new CottageDTO(c));
        }
     return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/cottage/{id}")
    public ResponseEntity<CottageDTO> getById(@PathVariable int id){
       CottageDTO cottage = new CottageDTO(cottageService.findOne(id));


        return  new ResponseEntity<>(cottage, HttpStatus.OK);
    }
}


