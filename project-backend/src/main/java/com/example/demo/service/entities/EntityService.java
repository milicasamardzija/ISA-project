package com.example.demo.service.entities;


import com.example.demo.model.entities.EntityClass;
import com.example.demo.repository.entities.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {

    private EntityRepository entityRepository;

    public EntityService(EntityRepository entityRepository){
        this.entityRepository = entityRepository;
    }

    public EntityClass findById(int id) { return entityRepository.findById(id);}

    public List<EntityClass> findAll() {
        return entityRepository.findAll();
    }

    public EntityClass findOne(Integer id) {
        return entityRepository.findById(id).orElseGet(null);
    }

    public Page<EntityClass> findAll(Pageable page) {
        return entityRepository.findAll(page);
    }

    public EntityClass EntityClass(EntityClass entity) {
        return entityRepository.save(entity);
    }

    public void remove(Integer id) {
        entityRepository.deleteById(id);
    }

    public void deleteById(Integer id) {
        entityRepository.deleteById(id);
    }

    public void save(EntityClass entity) {
        this.entityRepository.save(entity);
    }

}
