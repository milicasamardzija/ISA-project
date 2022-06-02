package com.example.demo.controller.entities;
import com.example.demo.dto.entities.AdventureDTO;

import com.example.demo.dto.entities.BoatDTO;
import com.example.demo.dto.entities.AdventureRequestDTO;
import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.dto.users.UserDTO;
import com.example.demo.dto.enums.CancelationType;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AdventureService;
import com.example.demo.service.users.InstructorService;
import com.example.demo.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Controller
@RequestMapping(value = "api/adventures")
public class AdventureController {

    private AdventureService adventureService;
    public UserService userService;
    public InstructorService instructorService;
    public AdventureController(AdventureService adventureService,UserService userService,InstructorService instructorService){
        this.adventureService = adventureService;
        this.userService=userService;
        this.instructorService=instructorService;
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

    @GetMapping("/getMyAdventures")
    public ResponseEntity<List<AdventureDTO>> getMyAdventures(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        User i = this.userService.findByEmail(user.getEmail());
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            if (adventure.getInstructor().getId() == i.getId()) {
                adventures.add(new AdventureDTO(adventure));
            }
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }


    @GetMapping("/findAdventure/{inputText}")
    public ResponseEntity<List<AdventureDTO>> findAdventures(@PathVariable String inputText){
        List<Adventure> allAdventures = adventureService.findAll();
        List<AdventureDTO> adventures = new ArrayList<>();
        for(Adventure adventure : allAdventures ) {
            if (adventure.getNameOfAdventure().toLowerCase(Locale.ROOT).contains(inputText.toLowerCase(Locale.ROOT))) {
                adventures.add(new AdventureDTO(adventure));
            }
        }
        return  new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @PostMapping("/search")
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

    @PostMapping("/addAdventure")
    public ResponseEntity<Adventure> addAdventure(@RequestBody AdventureRequestDTO adventureRequest){
        System.out.print("OVDE SAM");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        System.out.print("OVDE SAM1"+user.getId());
        Instructor i = this.instructorService.findByEmail(user.getEmail());
        System.out.print("OVDE SAM2 "+i.getId());
        Adventure a = new Adventure();
        a.setNameOfAdventure(adventureRequest.getNameOfAdventure());
        a.setName(adventureRequest.getNameOfAdventure());
        a.setMaxNumberOfPeople(adventureRequest.getMaxNumberOfPeople());
        a.setInstructorBiografy(adventureRequest.getInstructorBiografy());
        a.setPromoDescription(adventureRequest.getPromoDescription());
        a.setFishingEquipment(adventureRequest.getFishingEquipment());
        a.setRules(adventureRequest.getRules());
        a.setInstructor(i);
        if (adventureRequest.getCancelationType().equalsIgnoreCase("BESPLATNO")) {
            a.setCancelationType(CancelationType.BESPLATNO);
        }else {
            a.setCancelationType(CancelationType.SA_PROCENTOM);
        }
        if (a != null){
            adventureService.save(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            System.out.print("NULL je");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
