package com.example.demo.repository.entities;

import com.example.demo.model.entities.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure,Integer> {
}
