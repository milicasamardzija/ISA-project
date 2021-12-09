package com.example.demo.repository;

import com.example.demo.model.entities.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Integer> {

    @Query("select c from Cottage c, Image i join fetch c.id = i.id ")
    public List<Cottage> findCottagesWithPictures();

}
