package com.example.demo.controller.users;

import com.example.demo.dto.users.ClientDTO;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.Role;
import com.example.demo.model.users.User;
import com.example.demo.service.users.ClientService;
import com.example.demo.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/user")
public class UserController {

    private UserService userService;
    private ClientService clientService;

    public UserController(UserService userService, ClientService clientService){
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping("/profileClient")
    public ResponseEntity<ClientDTO> getProfileInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user.getRole().getName().equals("ROLE_CLIENT")){
            Client client = clientService.findById(user.getEmail());
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
