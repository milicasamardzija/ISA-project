package com.example.demo.service.entities;

import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;

import com.example.demo.model.entities.Boat;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import com.example.demo.repository.entities.BoatRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoatService {

    private BoatRepository boatRepository;
    private AdditionalServicesRepository additionalServicesRepository;

    public BoatService(BoatRepository boatRepository, AdditionalServicesRepository additionalServicesRepository){
        this.boatRepository = boatRepository;
        this.additionalServicesRepository =additionalServicesRepository;
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

    public List<Boat> searchBoats(SearchDTO searchParam) {
        List<Boat> ret = new ArrayList<>();

        for (Boat boat : this.findAll()){
            if (boat.getName().toLowerCase().contains(searchParam.getName().toLowerCase()) && boat.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && boat.getAddress().getStreet().toLowerCase().contains(searchParam.getStreet().toLowerCase())){
                ret.add(boat);
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


}
