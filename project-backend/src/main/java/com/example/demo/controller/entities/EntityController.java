package com.example.demo.controller.entities;

import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.entities.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/entities")
public class EntityController {

    private EntityService entityService;
    private AddressService addressService;

    public EntityController(EntityService entityService){
        this.entityService = entityService;
    }
    public EntityClass findById(int id) { return entityService.findById(id);}

    @GetMapping(value = "/getAllEntities")
    public ResponseEntity<List<EntityDTO>> getAll(){
        List<EntityClass> allEntities = entityService.findAll();
        List<EntityDTO> entities = new ArrayList<>();
        for(EntityClass entity : allEntities ) {
            EntityDTO entityDTO = new EntityDTO();
            entityDTO.setId(entity.getId());
            entityDTO.setGrade(entity.getGrade());
            entityDTO.setName(entity.getName());
            entityDTO.setPrice(entity.getPrice());
            entityDTO.setPromoDescription(entity.getPromoDescription());
            entityDTO.setRules(entity.getRules());
            Address a = entity.getAddress();
            entityDTO.setAddress(a);

            entities.add(entityDTO);
        }
        return  new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEntity/{deleteId}")
    public ResponseEntity<Void> deleteEntity (@PathVariable int deleteId) {
        System.out.print("Usla sam u funkciju");
        List<EntityClass> entities = entityService.findAll();
        for(EntityClass e : entities) {
            if (e.getId()== deleteId) {
                entityService.deleteById(e.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
