package com.example.demo.service.users;

import com.example.demo.model.users.Instructor;
import com.example.demo.repository.users.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor save(Instructor cottageOwner) {
        return instructorRepository.save(cottageOwner);

    }
}
