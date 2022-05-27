package com.example.demo.controller.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.CottageDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.users.CottageOwnerService;
import com.example.demo.service.users.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "api/cottages")
public class CottageController {

    private CottageService cottageService;
    private AdditionalServicesService aditionalServices;
    private CottageOwnerService cottageOwnerService;
    private AddressService addressService;
    private AdditionalServicesService additionalServicesService;

    public CottageController(CottageService cottageService, AdditionalServicesService additionalServicesService,  AddressService addressService, CottageOwnerService cottageOwner, AdditionalServicesService adServices){
        this.cottageService = cottageService;
        this.aditionalServices = adServices;
        this.cottageOwnerService = cottageOwner;
        this.addressService = addressService;
        this.additionalServicesService = additionalServicesService;
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
        System.out.print("Usla sam u metodu za get vikendica");
        List<Cottage> allOwnerCottages = cottageService.findAllOwnerCottages(id); //dobila sve vikendice
        List<CottageDTO> ret = new ArrayList<>();
        List<AdditionalServiceDTO> services = new ArrayList<>();
         if(allOwnerCottages.size() != 0){
                for(Cottage c : allOwnerCottages){
                    List<AdditionalService> allServices= this.aditionalServices.getServicesForCottage(c.getId()); //uzmem servise

                    if( allServices.size() != 0) {
                        for (AdditionalService a : allServices) {
                            services.add(new AdditionalServiceDTO(a));

                        }
                        CottageDTO cottage = new CottageDTO(c);
                        cottage.setAdditionalServices(services);
                        ret.add(cottage);
                    } else{
                        CottageDTO cottage = new CottageDTO(c);
                        cottage.setAdditionalServices(services);
                        ret.add(cottage);
                    }
                }
             return new ResponseEntity<>(ret, HttpStatus.OK);
            }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        System.out.print("id: " + id);
        cottageService.deleteById(id);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/cottage/{id}")
    public ResponseEntity<CottageDTO> getById(@PathVariable int id){
        CottageDTO cottage = new CottageDTO(cottageService.findOne(id));
        List<AdditionalService> allServices= this.aditionalServices.getServicesForCottage(id); //uzmem servise
         List<AdditionalServiceDTO> services = new ArrayList<>();
        if( allServices.size() != 0 ) {
            for (AdditionalService a : allServices) {

                services.add(new AdditionalServiceDTO(a));
                cottage.setAdditionalServices(services);
            }
        } else{
            cottage.setAdditionalServices(services);
        }
        return  new ResponseEntity<>(cottage, HttpStatus.OK);
    }

    @PostMapping("/newCottage")
    public ResponseEntity<Void> saveCottage(@RequestBody CottageDTO newCottage){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        CottageOwner owner = cottageOwnerService.finfById(user.getId()); //nasla ownera
        Cottage cottage = new Cottage( newCottage);
        cottage.setCottageOwner(owner);
        cottage.setAddress(addressService.save(newCottage.getAddress()));
        //cottage.setAdditionalServices(additionalServicesService.);
        List<AdditionalService> services = new ArrayList<>();
        if(newCottage.getAdditionalServices().size() != 0){
            for (AdditionalServiceDTO dto : newCottage.getAdditionalServices()){
               AdditionalService serv =  new AdditionalService(dto);

                services.add(new AdditionalService(dto));
            }
//            for (AdditionalService a: services ) {
//                a.setEntities(cottage);
//            }
            cottage.setAdditionalServices(additionalServicesService.saveAll(services));

        }

        this.cottageService.saveCottage(cottage);



        return  new ResponseEntity<>( HttpStatus.OK);

    }
}


