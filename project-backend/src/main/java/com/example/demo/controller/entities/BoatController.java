package com.example.demo.controller.entities;

import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Boat;
import com.example.demo.service.entities.BoatService;
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

    @PostMapping("/search")
    public ResponseEntity<List<Boat>> search(@RequestBody SearchDTO searchParam) {

        return new ResponseEntity<>(boatService.searchBoats(searchParam), HttpStatus.OK);
    }

}
