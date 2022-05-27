package com.example.demo.service.entities;

import com.example.demo.dto.entities.AdditionalServiceDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.Cottage;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import com.example.demo.repository.entities.CottageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CottageService {

    private CottageRepository cottageRepository;

    private  AdditionalServicesService aditionalServicesService;

    public CottageService(CottageRepository cottageRepository){
        this.cottageRepository = cottageRepository;
    }

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public Cottage findOne(Integer id) {
        Cottage cottage = cottageRepository.findById(id).orElseGet(null);
        return cottage;
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

    public List<Cottage> findAllOwnerCottages(int id){
        List<Cottage> ret = this.cottageRepository.findAllCottagesForOwner(id);

        return ret;
    }



    public void deleteById(int id) {
        Cottage c = this.cottageRepository.findById(id);
        System.out.print(c);
        this.cottageRepository.deleteById(id);
    }

    public Cottage saveCottage(Cottage newCottage){
        return this.cottageRepository.save(newCottage);
    }
}
