package com.example.demo.service.business;

import com.example.demo.model.business.Evaluate;
import com.example.demo.model.users.User;
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

    public Evaluate findById(int id) {
        return evaluateRepository.findById(id);
    }

    public void acceptEvaluate(Evaluate e) {
        Evaluate evaluate = this.evaluateRepository.findById(e.getId());
        evaluate.setAccepted(true);
        this.evaluateRepository.save(evaluate);
    }

    public void rejectEvaluate(Evaluate e) {
        e.setAccepted(false);
        this.evaluateRepository.save(e);
    }

    public void deleteById(int id) {
        Evaluate e = this.evaluateRepository.findById(id);
        this.evaluateRepository.delete(e);
    }

    public void save(Evaluate evaluate, User user){
        evaluate.setUserWhoSendsComplaint(user);
        this.evaluateRepository.save(evaluate);
    }
}
