package com.example.demo.service.entities;

import com.example.demo.model.entities.AdditionalService;
import com.example.demo.repository.entities.AdditionalServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServicesService {

    private AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServicesService(AdditionalServicesRepository repo){

        this.additionalServicesRepository = repo;
    }


    public List<AdditionalService> getServicesForCottage(int id){
        return  this.additionalServicesRepository.findAdditionalServicesForCottage(id);
    }

}
