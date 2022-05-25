package com.example.demo.service.users;

import com.example.demo.model.users.CottageOwner;
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

    public CottageOwner findByEmail(String id) {
        return this.cottageOwnerRepository.findByEmail(id);
    }

}
