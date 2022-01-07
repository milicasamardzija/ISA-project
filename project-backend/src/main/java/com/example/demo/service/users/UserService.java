package com.example.demo.service.users;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        u.setRole(new Role(("ROLE_CLIENT")));
        u.setEmail(userRequest.getEmail());
        u.setEnabled(true);
        return this.userRepository.save(u);
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
}
