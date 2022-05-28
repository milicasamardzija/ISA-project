package com.example.demo.repository.business;

import com.example.demo.model.business.ReservedTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedTermRepository extends JpaRepository<ReservedTerm, Integer> {
}
