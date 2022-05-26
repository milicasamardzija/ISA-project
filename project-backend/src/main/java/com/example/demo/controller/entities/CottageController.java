package com.example.demo.controller.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.service.entities.CottageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/reservationSearch")
    public ResponseEntity<List<EntityDTO>> searchReservation(@RequestBody ReservationSearchDTO searchParam){
        ArrayList<EntityDTO> ret = new ArrayList<>();

        for(EntityClass e : cottageService.searchReservation(searchParam)){
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}


