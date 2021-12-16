package com.example.demo.repository.entities;

import com.example.demo.model.entities.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Integer> {
}
