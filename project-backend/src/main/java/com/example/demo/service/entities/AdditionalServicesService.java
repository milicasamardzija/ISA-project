package com.example.demo.service.entities;

import com.example.demo.model.entities.AdditionalService;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalServicesService {

    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServicesService(AdditionalServicesRepository repo){

        this.additionalServicesRepository = repo;
    }

    public List<AdditionalService> saveAll(List<AdditionalService> services ){
        List<AdditionalService> ret = new ArrayList<>();
        for (AdditionalService a: services ) {
           ret.add(this.additionalServicesRepository.save(a)) ;
        }
        return  ret;
    };

//    public List<AdditionalService> saveAll(List<AdditionalService> services){
//        return  this.additionalServicesRepository.saveAll(services);
//    }

    public List<AdditionalService> getServicesForCottage(int id){
        return  this.additionalServicesRepository.findAdditionalServicesForCottage(id);
    }

    public AdditionalService save(AdditionalService additionalService) {
        return this.additionalServicesRepository.save(additionalService);
    }

    public List<AdditionalService> getAll(){
        return  this.additionalServicesRepository.findAll();
    }

    public AdditionalService findById(int id) {
        return this.additionalServicesRepository.findById(id);
    }
}
