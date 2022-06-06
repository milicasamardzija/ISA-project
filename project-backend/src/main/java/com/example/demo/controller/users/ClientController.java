package com.example.demo.controller.users;

import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.users.ClientDTO;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.service.users.ClientService;
import com.example.demo.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/client")
public class ClientController {

    private ClientService clientService;
    private UserService userService;

    public ClientController(ClientService clientService,UserService userService){

        this.clientService = clientService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
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

    @GetMapping("/getClient/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String id)
    {
            Client client = clientService.find(Integer.parseInt(id));
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/subscribedEntitites")
    public ResponseEntity<List<EntityDTO>> findSubscribedEnities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<EntityDTO> ret = new ArrayList<>();

        for (EntityClass e :clientService.findSubscribedEnities(user.getId())) {
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Client client = this.clientService.findById(user.getEmail());

        if (user != null){
            clientService.cancelSubsrciption(id, client);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> addSubscription(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Client client = this.clientService.findById(user.getEmail());

        if (user != null){

            if (clientService.addSubsrciptions(id, client)){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
