package com.example.demo.controller.entities;

import com.example.demo.dto.users.ClientDTO;
import com.example.demo.dto.users.CottageOwnerDTO;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.service.users.CottageOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/cottageOwner")
public class CottageOwnerController {

    private CottageOwnerService cottageOwnerService;

    public CottageOwnerController(CottageOwnerService cottageOwnerService){
        this.cottageOwnerService= cottageOwnerService;
    }

    public CottageOwner findByEmail(String id) { return cottageOwnerService.findByEmail(id); }

    @GetMapping("/profileCottageOwner")
    public ResponseEntity<CottageOwnerDTO> getProfileCottageOwner(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        if (user.getRole().getName().equals("ROLE_COTTAGE_OWNER")){
            CottageOwner cottageOwner = cottageOwnerService.findByEmail(user.getEmail());
            return new ResponseEntity<>(new CottageOwnerDTO(cottageOwner), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
