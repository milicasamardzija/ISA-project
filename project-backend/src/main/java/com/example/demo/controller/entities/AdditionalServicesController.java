package com.example.demo.controller.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.service.entities.AdditionalServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/additionalService")
public class AdditionalServicesController {

    public AdditionalServicesService additionalServicesService;

    public AdditionalServicesController(AdditionalServicesService service){
        this.additionalServicesService =service;
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<List<AdditionalServiceDTO>> servicesForCottage(@PathVariable  int id) {
        List<AdditionalService> allServices = this.additionalServicesService.getServicesForCottage(id);
        List<AdditionalServiceDTO> ret = new ArrayList<>();
        if(allServices != null  ) {
            for (AdditionalService a :  allServices  ) {
              ret.add(new AdditionalServiceDTO(a));
            }
            return new ResponseEntity<>(ret, HttpStatus.OK );
        }
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/servicess/{name}")
    public ResponseEntity<List<AdditionalServiceDTO>> servicesForAdventure(@PathVariable  String name) {
        System.out.print("IME JE"+name);
        List<AdditionalService> allServices = this.additionalServicesService.findAdditionalServicesForAdventure(name);
        List<AdditionalServiceDTO> ret = new ArrayList<>();
        if(allServices != null  ) {
            for (AdditionalService a :  allServices  ) {
                ret.add(new AdditionalServiceDTO(a));
            }
            System.out.print("Ispisi broj u listi:"+ ret.size());
            return new ResponseEntity<>(ret, HttpStatus.OK );
        }
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
