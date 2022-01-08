package com.example.demo.service;

import com.example.demo.model.users.CottageOwner;
import com.example.demo.repository.users.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CottageOwnerService {

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

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

}
