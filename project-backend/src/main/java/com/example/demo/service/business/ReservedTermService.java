package com.example.demo.service.business;

import com.example.demo.model.business.ReservedTerm;
import com.example.demo.repository.business.ReservedTermRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservedTermService {

    private ReservedTermRepository reservedTermRepository;

    public ReservedTermService(ReservedTermRepository reservedTermRepository) {
        this.reservedTermRepository = reservedTermRepository;
    }

    public void delete(ReservedTerm reservedTerm) {
        this.reservedTermRepository.delete(reservedTerm);
    }

    public void save(ReservedTerm reservedTerm){
        this.reservedTermRepository.save(reservedTerm);
    }
}
