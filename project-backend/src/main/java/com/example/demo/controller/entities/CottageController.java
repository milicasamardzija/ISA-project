package com.example.demo.controller.entities;

import com.example.demo.dto.entities.*;
import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AdditionalServicesService;
import com.example.demo.service.entities.AddressService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.service.entities.CottageService;
import com.example.demo.service.users.CottageOwnerService;
import com.example.demo.service.users.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@Controller
@RequestMapping(value = "api/cottages")
public class CottageController {

    private CottageService cottageService;

    private CottageOwnerService cottageOwnerService;
    private AddressService addressService;
    private AdditionalServicesService additionalServicesService;

    public CottageController(CottageService cottageService, AdditionalServicesService additionalServicesService,  AddressService addressService, CottageOwnerService cottageOwner){
        this.cottageService = cottageService;
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
    public ResponseEntity<List<CottageDTO>> search(@RequestBody SearchDTO searchParam) {
        List<CottageDTO> ret = new ArrayList<>();
        if( cottageService.searchStartPage(searchParam).size() != 0) {
            for (Cottage c : cottageService.searchStartPage(searchParam)) {
                ret.add(new CottageDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @PostMapping("/searchCottageOwner")
    public ResponseEntity<List<CottageDTO>> searchCottageOwner(@RequestBody SearchDTO searchParam) {
        List<CottageDTO> ret = new ArrayList<>();
        if( cottageService.searchCottages(searchParam).size() != 0) {
            for (Cottage c : cottageService.searchCottages(searchParam)) {
                    ret.add(new CottageDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @PostMapping("/reservationSearch")
    public ResponseEntity<List<EntityDTO>> searchReservation(@RequestBody ReservationSearchDTO searchParam){
        ArrayList<EntityDTO> ret = new ArrayList<>();

        for(EntityClass e : cottageService.searchReservation(searchParam)){
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/getMaxPeople/{id}")
    public ResponseEntity<Integer> getMaxPeople( @PathVariable int id){
        return new ResponseEntity<>(this.cottageService.getMaxPeople(id),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @GetMapping("/myCottages/{id}")
    public ResponseEntity<List<CottageDTO>> getOwnersCottages(@PathVariable int id){

        List<Cottage> allOwnerCottages = cottageService.findAllOwnerCottages(id);
        List<CottageDTO> ret = new ArrayList<>();
        List<AdditionalServiceDTO> services = new ArrayList<>();
         if(allOwnerCottages.size() != 0){
                for(Cottage c : allOwnerCottages){
                    List<AdditionalService> allServices= this.additionalServicesService.getServicesForCottage(c.getId());
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

    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @GetMapping("/ownerCottages")
    public ResponseEntity<List<CottageDTO>> getMyCottages(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User cottageOwner = (User)authentication.getPrincipal();
        List<Cottage> allOwnerCottages = cottageService.findAllOwnerCottages(cottageOwner.getId()); //dobila sve vikendice
        List<CottageDTO> ret = new ArrayList<>();
        List<AdditionalServiceDTO> services = new ArrayList<>();
        if(allOwnerCottages.size() != 0){
            for(Cottage c : allOwnerCottages){
                List<AdditionalService> allServices= this.additionalServicesService.getServicesForCottage(c.getId()); //uzmem servise
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

    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        cottageService.deleteById(id);

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/cottage/{id}")
    public ResponseEntity<CottageDTO> getById(@PathVariable int id){
        CottageDTO cottage = new CottageDTO(cottageService.findOne(id));
        List<AdditionalService> allServices= this.additionalServicesService.getServicesForCottage(id); //uzmem servise
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


    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @PostMapping("/newCottage")
    public ResponseEntity<Void> saveCottage(@RequestBody CottageDTO newCottage){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        CottageOwner owner = cottageOwnerService.findById(user.getId());
        Cottage cottage = new Cottage(newCottage);
        cottage.setCottageOwner(owner);
        cottage.setAddress(addressService.save(newCottage.getAddress()));
        if(newCottage.getAdditionalServices().size() != 0){

            for (AdditionalServiceDTO dto : newCottage.getAdditionalServices()){
                AdditionalService additionalService = new AdditionalService(dto);
                additionalService.setEntities(cottage);
                cottage.getAdditionalServices().add(additionalService);
            }
        }
        this.cottageService.saveCottage(cottage);
        return  new ResponseEntity<>( HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    @PostMapping("/editCottage")
    public ResponseEntity<Void> editCottage(@RequestBody CottageDTO editCottage) throws Exception {
        Set<AdditionalService> newServices = new HashSet<>();
        Cottage cottage = cottageService.findOne(editCottage.getId());
        if(editCottage.getAdditionalServices().size() != 0){

            for (AdditionalServiceDTO dto : editCottage.getAdditionalServices()){
                AdditionalService additionalService = new AdditionalService(dto);
                additionalService.setEntities(cottage);
                newServices.add(additionalService);

            }
        }
        try{
            this.cottageService.update(editCottage, newServices);
        }catch (Exception e ){
            System.out.print(e);
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>( HttpStatus.OK);

    }
}


