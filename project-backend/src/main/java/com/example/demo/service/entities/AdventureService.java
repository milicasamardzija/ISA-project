package com.example.demo.service.entities;

import com.example.demo.model.entities.Adventure;
import com.example.demo.repository.entities.AdventureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService {

    private AdventureRepository adventureRepository;

    public AdventureService(AdventureRepository adventureRepository){
        this.adventureRepository = adventureRepository;
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

}
