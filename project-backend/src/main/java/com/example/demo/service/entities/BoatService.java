package com.example.demo.service.entities;

import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;

import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.BoatOwner;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import com.example.demo.repository.entities.BoatRepository;
import com.example.demo.service.users.BoatOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoatService {

    private BoatRepository boatRepository;
    private AdditionalServicesRepository additionalServicesRepository;
    private BoatOwnerService boatOwnerService;

    public BoatService(BoatRepository boatRepository,BoatOwnerService ownerService, AdditionalServicesRepository additionalServicesRepository){
        this.boatRepository = boatRepository;
        this.additionalServicesRepository =additionalServicesRepository;
        this.boatOwnerService = ownerService;
    }

    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    public Boat findOne(Integer id) {
        return boatRepository.findById(id).orElseGet(null);
    }

    public Page<Boat> findAll(Pageable page) {
        return boatRepository.findAll( page);
    }

    public Boat save(Boat boat) {
        return boatRepository.save(boat);
    }

    public void remove(Integer id) {
        boatRepository.deleteById(id);
    }

    public List<Boat> searchBoatsStartPage(SearchDTO searchParam) {
        List<Boat> ret = new ArrayList<>();

        for (Boat cottage : this.findAll()) {

            //prazno sve
            if( searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                ret.add(cottage);
            }
            //prazno ime
            else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if( cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazno ime i ulica
            else if(searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if(cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazno ime i grad
            else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if( cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())){
                    ret.add(cottage);
                }
            }
            //prazna ulica i grad
            else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
            //prazno grad
            else if(!searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
            //prazna ulica
            else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                if(cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) ){
                    ret.add(cottage);
                }
            }
        }


        return  ret;
    }

    public List<Boat> searchBoatsOwner(SearchDTO searchParam) {
        List<Boat> ret = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if(user.getRole().getName().equals("ROLE_BOAT_OWNER")){
            List<Boat> ownersCottages = this.findBoatsForBoatOwner(user.getId());
            for (Boat c : ownersCottages ) {
                //prazno sve
                if( searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    //vratice sve
                    ret.add(c);
                }
                //prazno ime
                else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if( c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazno ime i ulica
                else if(searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if(c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazno ime i grad
                else if(searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if( c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())){
                        ret.add(c);
                    }
                }
                //prazna ulica i grad
                else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) ){
                        ret.add(c);
                    }
                }
                //prazno grad
                else if(!searchParam.getName().equals("") && !searchParam.getStreet().equals("") &&  searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && c.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) ){
                        ret.add(c);
                    }
                }
                //prazna ulica
                else if(!searchParam.getName().equals("") && searchParam.getStreet().equals("") &&  !searchParam.getCity().equals("")){
                    if(c.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && c.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) ){
                        ret.add(c);
                    }
                }
            }
        }
        return  ret;
    }

    public List<Boat> findBoatsForBoatOwner(int id){
        return  boatRepository.findBoatsForBoatOwner(id);
    }

    public void update(BoatDTO updatedBoat, Set<AdditionalService> services){
        Boat boatNewState = this.boatRepository.findBoatWithServices(updatedBoat.getId());
        boatNewState.setName(updatedBoat.getName());
        boatNewState.setAddress(updatedBoat.getAddress());
        boatNewState.setPromoDescription(updatedBoat.getPromoDescription());
        boatNewState.setRules(updatedBoat.getRules());
        boatNewState.setPrice(updatedBoat.getPrice());
        boatNewState.setBoatType(updatedBoat.getBoatType());
        boatNewState.setLenght(updatedBoat.getLenght());
        boatNewState.setMotorNumber(updatedBoat.getMotorNumber());
        boatNewState.setPower(updatedBoat.getPower());
        boatNewState.setMaxSpeed(boatNewState.getMaxSpeed());
        boatNewState.setNavigationEquipment(updatedBoat.getNavigationEquipment());
        boatNewState.setFishingEquipment(updatedBoat.getFishingEquipment());
        boatNewState.setQuantity(boatNewState.getQuantity());
        boatNewState.setCancelationType(updatedBoat.getCancelationType());
        boatNewState.setImage(updatedBoat.getImages());
        //List<AdditionalService> current = additionalServicesRepository.findAdditionalServicesForCottage(updatedBoat.getId());
     if(updatedBoat.getAdditionalServices().size() != 0) {
         for (AdditionalService a :
                 boatNewState.getAdditionalServices()) {
             additionalServicesRepository.deleteById(a.getId());
             //boatNewState.getAdditionalServices().remove(a);
         }

     }
        boatNewState.setAdditionalServices(new HashSet<>());
         if(services.size() != 0) {
             for (AdditionalService newA : services ) {
                 //newA.setEntities(boatNewState);
                 boatNewState.getAdditionalServices().add(newA);
             }
         }
          boatRepository.save(boatNewState);

    }

    public BoatOwner findOwnerForBoat(int id) {
        return boatRepository.findBoatOwner(id);
    }

    public void deleteById(int id) {
        Boat boat = this.boatRepository.findBoatWithServices(id);
        BoatOwner owner = this.findOwnerForBoat(id);
        BoatOwner ownerWithBoats = boatOwnerService.getOwnerWithBoats(owner.getId()); //zasto vrati samo 1


        for(Boat c : ownerWithBoats.getBoatList()){
            if(c.getId() == id) {
                ownerWithBoats.getBoatList().remove(c);
                boatOwnerService.save(ownerWithBoats);
                break;
            }
        }
        //brisem sve servise u kom je boat id strani kljuc
        for(AdditionalService a: boat.getAdditionalServices()){
            if(a.getEntities().getId() == id ){
                additionalServicesRepository.deleteById(a.getId());
            }
        }

        this.boatRepository.delete(boat);
    }
}
