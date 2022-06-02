package com.example.demo.service.entities;

import com.example.demo.dto.entities.SearchDTO;
import com.example.demo.model.entities.Adventure;
import com.example.demo.model.entities.Boat;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.AdventureRepository;
import com.example.demo.service.users.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureService {

    private AdventureRepository adventureRepository;
    private UserService userService;

    public AdventureService(AdventureRepository adventureRepository, UserService userService){
        this.adventureRepository = adventureRepository;
        this.userService = userService;
    }

    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    public Adventure findOne(Integer id) {
        return adventureRepository.findById(id).orElseGet(null);
    }

    public Page<Adventure> findAll(Pageable page) {
        return adventureRepository.findAll( page);
    }

    public Adventure save(Adventure adventure) { return adventureRepository.save(adventure); }

    public void remove(Integer id) {
        adventureRepository.deleteById(id);
    }

    public List<Adventure> searchAdventures(SearchDTO searchParam) {
        List<Adventure> ret = new ArrayList<>();

        for (Adventure adventure : this.findAll()){
            if (adventure.getName().toLowerCase().contains(searchParam.getName().toLowerCase()) && adventure.getAddress().getCity().toLowerCase().contains(searchParam.getCity().toLowerCase()) && adventure.getAddress().getStreet().toLowerCase().contains(searchParam.getStreet().toLowerCase())){
                ret.add(adventure);
            }
        }

        return  ret;
    }

    public User fetchInstructorByAdventure(int id) {
        Adventure a = this.adventureRepository.fetchInstructor(id);
        return this.userService.findByEmail(a.getInstructor().getEmail());
    }

    public void deleteByNameOfAdventure(String namee) {
        Adventure a = this.adventureRepository.findByNameOfAdventure(namee);
        System.out.print("NAsla sam avanturu"+ a.getNameOfAdventure());
        this.adventureRepository.delete(a);
    }
}
