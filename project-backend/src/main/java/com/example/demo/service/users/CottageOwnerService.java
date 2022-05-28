package com.example.demo.service.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class CottageOwnerService {

    private CottageOwnerRepository cottageOwnerRepository;

    private EntityRepository entityRepository;

    public CottageOwnerService(CottageOwnerRepository cottageOwnerRepository, EntityRepository entityRepository){
        this.cottageOwnerRepository = cottageOwnerRepository;
        this.entityRepository= entityRepository;
    }

    public CottageOwner save(CottageOwner cottageOwner) {
        return cottageOwnerRepository.save(cottageOwner);

    }
    public  CottageOwner finfById(int id) {
        return cottageOwnerRepository.getById(id);
    }
    public CottageOwner findByEmail(String id) {
        return this.cottageOwnerRepository.findByEmail(id);
    }

    public void remove(Integer id) {
        cottageOwnerRepository.deleteById(id);}


}
