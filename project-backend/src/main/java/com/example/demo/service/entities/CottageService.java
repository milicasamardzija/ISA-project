package com.example.demo.service.entities;

import com.example.demo.model.entities.Cottage;
import com.example.demo.repository.entities.CottageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

}
