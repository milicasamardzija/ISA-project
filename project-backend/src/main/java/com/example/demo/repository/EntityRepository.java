package com.example.demo.repository;

import com.example.demo.model.entities.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<EntityClass, Integer> {
}
