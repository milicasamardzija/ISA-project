package com.example.demo.service.users;

import com.example.demo.model.entities.Address;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.repository.users.AdministratorRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    public AdministratorService (AdministratorRepository administratorRepository,PasswordEncoder passwordEncoder,RoleService roleService) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

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
        Administrator a = new Administrator();
        a.setName(administrator.getName());
        a.setSurname(administrator.getSurname());
        a.setEnabled(true);
        a.setMust_change_password(true);
        a.setEmail(administrator.getEmail());
        a.setTelephone(administrator.getTelephone());
        a.setPassword(administrator.getPassword());
        a.setAddress(administrator.getAddress());
        a.setRole(roleService.findByName("ROLE_ADMIN"));
        a.setPassword(passwordEncoder.encode(administrator.getPassword()));
        return administratorRepository.save(a);

    }

    public void remove(Integer id) {
        administratorRepository.deleteById(id);
    }

}
