package com.example.demo.repository.business;

import com.example.demo.model.business.Complaint;
import com.example.demo.model.business.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {
    Evaluate findById(int id);
    Evaluate save(Evaluate evaluate);
}
