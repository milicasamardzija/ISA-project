package com.example.demo.service.users;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.CottageOwnerRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AddressService addressService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AddressService addressService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
    }

    public User findById(int id) {
        return this.userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User save(UserRequest userRequest){
        User u = new User();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setRole(new Role((userRequest.getRole()))); //ovo ce napraviti razlicite role
        u.setEmail(userRequest.getEmail());
        u.setEnabled(true);   // odmah odobreno
        u.setAddress(addressService.save(userRequest.getAddress()));
        // return this.userRepository.save(u);
        return u;
    }

    public void update(UpdateUserDTO updatedUser) {
        User userToUpdate = userRepository.findById(updatedUser.getId());
        userToUpdate.setName(updatedUser.getFirstname());
        userToUpdate.setSurname(updatedUser.getLastname());
        userToUpdate.setAddress(updatedUser.getAddress());
        userToUpdate.setTelephone(updatedUser.getTelephone());
        userRepository.save(userToUpdate);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    
    public User saveClient(UserRequest userRequest){
        User u = new User();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setRole(new Role((userRequest.getRole()))); //ovo ce napraviti razlicite role
        u.setEmail(userRequest.getEmail());
        u.setEnabled(true);   // odmah odobreno
        u.setAddress(userRequest.getAddress());

       return this.userRepository.save(u);

    }

}
