package com.example.demo.service.entities;

import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ReservationRepository;
import com.example.demo.repository.entities.AdventureRepository;
import com.example.demo.service.users.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureService {

    private AdventureRepository adventureRepository;
    private UserService userService;
    private ReservationRepository reservationRepository;
    private EntityService entityService;

    public AdventureService(EntityService entityService,AdventureRepository adventureRepository, UserService userService,ReservationRepository reservationRepository){
        this.adventureRepository = adventureRepository;
        this.userService = userService;
        this.reservationRepository=reservationRepository;
        this.entityService=entityService;
    }

    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    public Adventure findOne(Integer id) {
        return adventureRepository.findById(id).orElseGet(null);
    }

    public Page<Adventure> findAll(Pageable page) {
        return adventureRepository.findAll( page);
    }

    public Adventure save(Adventure adventure) { return adventureRepository.save(adventure); }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }

    public List<Adventure> searchAdventures(SearchDTO searchParam) {
        List<Adventure> ret = new ArrayList<>();

        for (Adventure adventure : this.findAll()){
            if (adventure.getName().toLowerCase().contains(searchParam.getName().toLowerCase()) && adventure.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && adventure.getAddress().getStreet().toLowerCase().contains(searchParam.getStreet().toLowerCase())){
                ret.add(adventure);
            }
        }

        return  ret;
    }

    public User fetchInstructorByAdventure(int id) {
        Adventure a = this.adventureRepository.fetchInstructor(id);
        return this.userService.findByEmail(a.getInstructor().getEmail());
    }


    public List<Adventure> searchAdventuresStartPage(SearchDTO searchParam) {
        List<Adventure> ret = new ArrayList<>();

        for (Adventure cottage : this.findAll()) {

            //prazno sve
            if (searchParam.getName().equals("") && searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                ret.add(cottage);
            }
            //prazno ime
            else if (searchParam.getName().equals("") && !searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno ime i ulica
            else if (searchParam.getName().equals("") && searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno ime i grad
            else if (searchParam.getName().equals("") && !searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazna ulica i grad
            else if (!searchParam.getName().equals("") && searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazno grad
            else if (!searchParam.getName().equals("") && !searchParam.getStreet().equals("") && searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getStreet().toLowerCase().equals(searchParam.getStreet().toLowerCase())) {
                    ret.add(cottage);
                }
            }
            //prazna ulica
            else if (!searchParam.getName().equals("") && searchParam.getStreet().equals("") && !searchParam.getCity().equals("")) {
                if (cottage.getName().toLowerCase().equals(searchParam.getName().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase())) {
                    ret.add(cottage);
                }
            }
        }


        return ret;
    }
    public void deleteByNameOfAdventure(String namee) {
        Adventure a = this.adventureRepository.findByNameOfAdventure(namee);
        System.out.print("NAsla sam avanturu"+ a.getNameOfAdventure());
        this.adventureRepository.delete(a);
    }

    public boolean deleteAdventureByName(Adventure a) {
        List<Reservation> reservations = this.reservationRepository.findAll();
        System.out.print("Broj rezervacija je"+reservations.size());
        for (Reservation r : reservations) {
            EntityClass e = this.entityService.findById(r.getEntity().getId());
            System.out.print("NAZIVI SU"+ e.getName());
            if (e.getName().contains(a.getNameOfAdventure())) {
                System.out.print("HEJ TI");
                return false;
            }
        }
        this.adventureRepository.delete(a);
        return true;
    }
}
