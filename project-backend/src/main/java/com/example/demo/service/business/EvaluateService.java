package com.example.demo.service.business;

import com.example.demo.model.business.Evaluate;
import com.example.demo.repository.business.ComplaintRepository;
import com.example.demo.repository.business.EvaluateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {
    EvaluateRepository evaluateRepository;

    public EvaluateService(EvaluateRepository evaluateRepository){
        this.evaluateRepository = evaluateRepository;
    }

    public List<Evaluate> findAll() {
        return evaluateRepository.findAll();
    }
}
