package com.example.demo.service.users;

import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.repository.users.AdministratorRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Administrator findOne(Integer id) {
        return administratorRepository.findById(id).orElseGet(null);
    }

    public Page<Administrator> findAll(Pageable page) {
        return administratorRepository.findAll( page);
    }

    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);

    }

    public void remove(Integer id) {
        administratorRepository.deleteById(id);
    }

}
