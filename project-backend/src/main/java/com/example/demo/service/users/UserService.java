package com.example.demo.service.users;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.entities.AddressService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService; 
    private AddressService addressService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AddressService addressService, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.roleService = roleService;
    }

    public User findById(int id) {
        return this.userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
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
        user.setMust_change_password(false);
        userRepository.save(user);
    }
    public void updateEnableState(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void deleteById(User user){
        this.userRepository.delete(user);
        this.roleService.delete(user.getRole());
    }
    public void deleteUserById(int id){
        User u = this.userRepository.findById(id);
        this.userRepository.delete(u);
    }
    public User save(UserRequest userRequest){
        User u = new User();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setRole(new Role((userRequest.getRole()))); //ovo ce napraviti razlicite role
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);   // odmah odobreno
        u.setAddress(addressService.save(userRequest.getAddress()));
        this.userRepository.save(u);
        return u;
    }
    public User saveUser(User user){
        User u = new User();
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setTelephone(user.getTelephone());
        u.setRole(user.getRole());
        u.setEmail(user.getEmail());
        u.setEnabled(true);
        u.setAddress(addressService.save(user.getAddress()));
        this.userRepository.save(u);
        return u;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> RequestUser() {
        List<User> users = new ArrayList<>();
        for (User user : this.findAll()){
            if (user.getEnabled()== false) {
                users.add(user);
            }
        }
        return  users;
    }

    public User saveClient(UserRequest userRequest){
        User u = new User();
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setTelephone(userRequest.getTelephone());
        u.setRole(new Role((userRequest.getRole()))); 
        u.setEmail(userRequest.getEmail());
        u.setEnabled(false);   
        u.setAddress(addressService.save(userRequest.getAddress()));
        return this.userRepository.save(u);
    }

}
