package com.example.demo.controller.entities;

import com.example.demo.dto.entities.EntityDTO;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.service.entities.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/entities")
public class EntityController {

    private EntityService entityService;

    public EntityController(EntityService entityService){
        this.entityService = entityService;
    }

    @GetMapping
    public ResponseEntity<List<EntityDTO>> getAll(){
        List<EntityClass> allEntities = entityService.findAll();
        List<EntityDTO> entities = new ArrayList<>();
        for(EntityClass entity : allEntities ) {
            entities.add(new EntityDTO(entity));
        }
        return  new ResponseEntity<>(entities, HttpStatus.OK);
    }

}
