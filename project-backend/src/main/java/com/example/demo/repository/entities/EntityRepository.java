package com.example.demo.repository.entities;

import com.example.demo.model.entities.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<EntityClass, Integer> {



    EntityClass findById(int id);
    void deleteById(int id);
}
