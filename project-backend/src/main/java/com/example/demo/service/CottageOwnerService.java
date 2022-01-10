package com.example.demo.service;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CottageOwnerService {

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;
    @Autowired
    private UserService userService;

    public List<CottageOwner> findAll() {
        return cottageOwnerRepository.findAll();
    }

    public CottageOwner findOne(Integer id) {
        return cottageOwnerRepository.findById(id).orElseGet(null);
    }

    public Page<CottageOwner> findAll(Pageable page) {
        return cottageOwnerRepository.findAll( page);
    }

    public CottageOwner save(CottageOwner cottageOwner) {
        return cottageOwnerRepository.save(cottageOwner);

    }

    public void remove(Integer id) {
        cottageOwnerRepository.deleteById(id);
    }

    public User fetchCottageOwnerByCottage(int idCottage){
        Cottage cottage = this.cottageOwnerRepository.fetchCottageOwnerByCottage(idCottage);
        return this.userService.findByEmail(cottage.getCottageOwner().getEmail());
    }
}
