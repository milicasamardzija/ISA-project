package com.example.demo.controller.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.BoatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/boats")
public class BoatController {

    private BoatService boatService;
    private AdditionalServicesService additionalServicesService;

    public BoatController(BoatService boatService, AdditionalServicesService adService) {
        this.boatService = boatService;
        this.additionalServicesService = adService;
    }

    @GetMapping
    public ResponseEntity<List<BoatDTO>> getAll() {
        List<Boat> allBoats = boatService.findAll();
        List<BoatDTO> cottages = new ArrayList<>();
        for (Boat boat : allBoats) {
            cottages.add(new BoatDTO(boat));
        }
        return new ResponseEntity<>(cottages, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Boat>> search(@RequestBody SearchDTO searchParam) {

        return new ResponseEntity<>(boatService.searchBoats(searchParam), HttpStatus.OK);
    }

    @GetMapping("/myBoats/{id}")
    public ResponseEntity<List<BoatDTO>> getOwnersBoats(@PathVariable int id) {

        List<Boat> allOwnerBoats = boatService.findBoatsForBoatOwner(id); //dobila sve vikendice
        List<BoatDTO> ret = new ArrayList<>();
        List<AdditionalServiceDTO> services = new ArrayList<>();
        if (allOwnerBoats.size() != 0) {
            for (Boat b : allOwnerBoats) {
                List<AdditionalService> allServices = this.additionalServicesService.getServicesForCottage(b.getId()); //uzmem servise
                if (allServices.size() != 0) {
                    for (AdditionalService a : allServices) {
                        services.add(new AdditionalServiceDTO(a));

                    }
                    BoatDTO boat = new BoatDTO(b);
                    boat.setAdditionalServices(services);
                    ret.add(boat);
                } else {
                    BoatDTO boat = new BoatDTO(b);
                    boat.setAdditionalServices(services);
                    ret.add(boat);
                }
            }
                return new ResponseEntity<>(ret, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
