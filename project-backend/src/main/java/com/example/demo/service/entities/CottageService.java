package com.example.demo.service.entities;

import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Cottage;
import com.example.demo.repository.entities.CottageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
