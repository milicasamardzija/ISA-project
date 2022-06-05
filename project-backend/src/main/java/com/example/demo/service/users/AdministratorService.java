package com.example.demo.service.users;

import com.example.demo.dto.enums.Role;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.repository.users.AdministratorRepository;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.service.entities.AddressService;
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
    private AddressService addressService;

    public AdministratorService (AddressService addressService,AdministratorRepository administratorRepository,PasswordEncoder passwordEncoder,RoleService roleService) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.addressService = addressService;
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
        a.setPassword(passwordEncoder.encode(administrator.getPassword()));

        Role r = this.roleService.findByName("ROLE_ADMIN");
        if (r==null) {
           r = this.roleService.findByName("ROLE_PREDEF_ADMIN");
           if (r==null) {
               Role newRole = new Role(administrator.getRole());
               this.roleService.save(newRole);
               a.setRole(newRole);
           }
        }else {
            a.setRole(r);
        }
        return administratorRepository.save(a);
    }

    public void remove(Integer id) {
        administratorRepository.deleteById(id);
    }

    public void saveAdmin(UserRequest userRequest) {
        Administrator a = new Administrator();
        a.setName(userRequest.getFirstname());
        a.setSurname(userRequest.getLastname());
        a.setEnabled(true);
        a.setMust_change_password(true);
        a.setEmail(userRequest.getEmail());
        a.setTelephone(userRequest.getTelephone());
        Address address = new Address(userRequest.getAddress().getCountry(),userRequest.getAddress().getCity(),userRequest.getAddress().getStreet(),userRequest.getAddress().getNumber(),0,0);

        a.setAddress(addressService.save(address));
        a.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Role r = this.roleService.findByName("ROLE_ADMIN");
        if (r==null) {
            r = this.roleService.findByName("ROLE_PREDEF_ADMIN");
            if (r==null) {
                Role newRole = new Role(userRequest.getRole());
                this.roleService.save(newRole);
                a.setRole(newRole);
            }
        }else {
            a.setRole(r);
        }
         administratorRepository.save(a);
    }
}
