package com.example.demo.controller.users;

import com.example.demo.dto.entities.CottageOwnerDTO;
import com.example.demo.dto.users.AdministratorDTO;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.service.CottageOwnerService;
import com.example.demo.service.users.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/administrator")
public class AdministratorController {
    private AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
}
