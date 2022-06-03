package com.example.demo.controller.entities;
import com.example.demo.dto.business.ReservationSearchDTO;
import com.example.demo.dto.entities.AdventureDTO;
import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.entities.EntityClass;
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

   /* @PostMapping("/search")
    public ResponseEntity<List<Adventure>> search(@RequestBody SearchDTO searchParam) {
        return new ResponseEntity<>(adventureService.searchAdventures(searchParam), HttpStatus.OK);
    }*/

    @GetMapping("/instructorUser/{id}")
    public  ResponseEntity<UserDTO> fetchInstructor(@PathVariable int id){
        User user = this.adventureService.fetchInstructorByAdventure(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<AdventureDTO>> searchStartPage(@RequestBody SearchDTO searchParam) {
        List<AdventureDTO> ret = new ArrayList<>();
        if( adventureService.searchAdventuresStartPage(searchParam).size() != 0) {
            for (Adventure c : adventureService.searchAdventuresStartPage(searchParam)) {
                ret.add(new AdventureDTO(c));
            }
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/reservationSearch")
    public ResponseEntity<List<EntityDTO>> searchReservation(@RequestBody ReservationSearchDTO searchParam){
        ArrayList<EntityDTO> ret = new ArrayList<>();

        for(EntityClass e : adventureService.searchReservation(searchParam)){
            ret.add(new EntityDTO(e));
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping("/getMaxPeople/{id}")
    public ResponseEntity<Integer> getMaxPeople( @PathVariable int id){
        return new ResponseEntity<>(this.adventureService.getMaxPeople(id),HttpStatus.OK);
    }
}
