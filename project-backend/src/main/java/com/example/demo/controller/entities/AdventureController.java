package com.example.demo.controller.entities;
import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AdventureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/adventures")
public class AdventureController {

    private AdventureService adventureService;

    public AdventureController(AdventureService adventureService){
        this.adventureService = adventureService;
    }

    @GetMapping
    public ResponseEntity<List<AdventureDTO>> getAll(){
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            adventures.add(new AdventureDTO(adventure));
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Adventure>> search(@RequestBody SearchDTO searchParam) {
        return new ResponseEntity<>(adventureService.searchAdventures(searchParam), HttpStatus.OK);
    }

    @GetMapping("/instructorUser/{id}")
    public  ResponseEntity<UserDTO> fetchInstructor(@PathVariable int id){
        User user = this.adventureService.fetchInstructorByAdventure(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
}
