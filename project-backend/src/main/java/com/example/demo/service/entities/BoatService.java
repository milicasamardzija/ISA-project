package com.example.demo.service.entities;

import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Boat;
import com.example.demo.repository.entities.BoatRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoatService {

    private BoatRepository boatRepository;

    public BoatService(BoatRepository boatRepository){
        this.boatRepository = boatRepository;
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

}
