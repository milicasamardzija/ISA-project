package com.example.demo.service.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageOwnerService {

    private CottageOwnerRepository cottageOwnerRepository;
    private UserService userService;
    private EntityRepository entityRepository;

    public CottageOwnerService(CottageOwnerRepository cottageOwnerRepository, EntityRepository entityRepository, UserService userService){
        this.cottageOwnerRepository = cottageOwnerRepository;
        this.entityRepository= entityRepository;
        this.userService = userService;
    }

    public CottageOwner save(CottageOwner cottageOwner) {
        cottageOwner.setCottageList(new ArrayList<>());
        return cottageOwnerRepository.save(cottageOwner);

    }
    public  CottageOwner findById(int id) {
        return cottageOwnerRepository.getById(id);
    }

    public CottageOwner findByEmail(String id) {
        return this.cottageOwnerRepository.findByEmail(id);
    }

    public void remove(Integer id) {
        cottageOwnerRepository.deleteById(id);
    }

    public List<CottageOwner> findAll() {
        return cottageOwnerRepository.findAll();
    }

    public CottageOwner findOne(Integer id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public User fetchCottageOwnerByCottage(int idCottage){
        Cottage cottage = this.cottageOwnerRepository.fetchCottageOwnerByCottage(idCottage);
        return this.userService.findByEmail(cottage.getCottageOwner().getEmail());
    }
}
