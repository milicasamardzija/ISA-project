package com.example.demo.repository.entities;

import com.example.demo.model.entities.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Integer> {

    Cottage findById(int id);
    void deleteById(int id);

    @Query("select co from Cottage co left join fetch  co.cottageOwner c where c.id=?1")
    List<Cottage> findAllCottagesForOwner(int id);
}
