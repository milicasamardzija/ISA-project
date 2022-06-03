package com.example.demo.service.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.AdventureRepository;
import com.example.demo.service.users.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class AdventureService {

    private AdventureRepository adventureRepository;
    private UserService userService;

    public AdventureService(AdventureRepository adventureRepository, UserService userService){
        this.adventureRepository = adventureRepository;
        this.userService = userService;
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

    public List<EntityClass> searchReservation(ReservationSearchDTO searchParam){
        List<EntityClass> ret = new ArrayList<>();
        String[] time = searchParam.getTime().split(":");
        String hour = time[0];
        String minutes = time[1];

        Calendar calStart = Calendar.getInstance();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calStart.setTime(searchParam.getDate());
        calStart.add(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calStart.add(Calendar.MINUTE, Integer.parseInt(minutes));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
        calEnd.setTime(searchParam.getDate());
        calEnd.add(Calendar.DAY_OF_YEAR, searchParam.getNumber());

        Boolean isNotReserved = true;
        for (Adventure cottage : this.findAll()) {
            for (ReservedTerm term : cottage.getReservedTerms()) {
                if (!term.isCanceled()) {
                    //  |***term***|
                    //      |----cal---|
                    if (calStart.getTime().after(term.getDateStart()) && calStart.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                    //  |----cal---|
                    //     |***term***|
                    if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd()) && calEnd.getTime().after(term.getDateStart())) {
                        isNotReserved = false;
                    }
                    //  |------cal------|
                    //     |***term***|
                    if (calStart.getTime().before(term.getDateStart()) && calEnd.getTime().after(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                    //    |----cal----|
                    //  |******term******|
                    if (calStart.getTime().after(term.getDateStart()) && calEnd.getTime().before(term.getDateEnd())
                    ) {
                        isNotReserved = false;
                    }
                }

            }

            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //country + people
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            //city + people
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getMaxNumberOfPeople()  && searchParam.getPeople() > 0) {

                ret.add(cottage);
            }
            //city + country
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0) {
                ret.add(cottage);
            }
            //no city, no country, no people
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() == 0) {
                ret.add(cottage);
            }
            //country
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().equals(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0) {
                ret.add(cottage);
            }
            //city
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().equals(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() == 0) {
                ret.add(cottage);
            }
            //people
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getMaxNumberOfPeople() && searchParam.getPeople() > 0) {
                ret.add(cottage);
            }
            isNotReserved = true;
        }
        return ret;
    }

    public Integer getMaxPeople(int id) {
        Adventure adventure = this.findOne(id);
        return adventure.getMaxNumberOfPeople();
    }

}
