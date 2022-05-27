package com.example.demo.service.entities;

import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.business.ReservedTerm;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.repository.entities.CottageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class CottageService {

    private CottageRepository cottageRepository;

    public CottageService(CottageRepository cottageRepository){
        this.cottageRepository = cottageRepository;
    }

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public Cottage findOne(Integer id) {
        return cottageRepository.findById(id).orElseGet(null);
    }

    public Page<Cottage> findAll(Pageable page) {
        return cottageRepository.findAll( page);
    }

    public Cottage save(Cottage course) {
        return cottageRepository.save(course);
    }

    public void remove(Integer id) {
        cottageRepository.deleteById(id);
    }

    public List<Cottage> searchCottages(SearchDTO searchParam){
        List<Cottage> ret = new ArrayList<>();

        for (Cottage cottage : this.findAll()){
            if (cottage.getName().toLowerCase().contains(searchParam.getName().toLowerCase()) && cottage.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && cottage.getAddress().getStreet().toLowerCase().contains(searchParam.getStreet().toLowerCase())){
                ret.add(cottage);
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
        for (Cottage cottage : this.findAll()){

            for (ReservedTerm term : cottage.getReservedTerms()) {
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
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().contains(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getBedsByRoom()*cottage.getRoomsNumber()){
                ret.add(cottage);
            }
            //country + people
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().contains(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() <= cottage.getBedsByRoom()*cottage.getRoomsNumber()){
                ret.add(cottage);
            }
            //city + people
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getBedsByRoom()*cottage.getRoomsNumber()){
                ret.add(cottage);
            }
            //city + country
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && cottage.getAddress().getCountry().toLowerCase().contains(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0){
                ret.add(cottage);
            }
            //no city, no country, no people
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() == 0){
                ret.add(cottage);
            }
            //country
            if (isNotReserved && searchParam.getCity().equals("") && cottage.getAddress().getCountry().toLowerCase().contains(searchParam.getCountry().toLowerCase()) && searchParam.getPeople() == 0){
                ret.add(cottage);
            }
            //city
            if (isNotReserved && cottage.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase())  && searchParam.getCountry().equals("") && searchParam.getPeople() == 0){
                ret.add(cottage);
            }
            //people
            if (isNotReserved && searchParam.getCity().equals("") && searchParam.getCountry().equals("") && searchParam.getPeople() <= cottage.getBedsByRoom()*cottage.getRoomsNumber()){
                ret.add(cottage);
            }
            isNotReserved = true;
        }

        return ret;
    }

}
