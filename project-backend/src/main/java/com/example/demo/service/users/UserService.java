package com.example.demo.service.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User findById(int id) {
        return this.userRepository.findById(id);
    }

    User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    User save(UserRequest userRequest){
        User u = new User();
        u.setPassword(userRequest.getPassword());
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getLastname());
        u.setRole(new Role(("ROLE_CLIENT")));
        u.setEmail(userRequest.getEmail());
        u.setEnabled(true);
        return this.userRepository.save(u);
    }
}
