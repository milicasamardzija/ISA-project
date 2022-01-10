package com.example.demo.repository.users;

import com.example.demo.model.users.CottageOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<CottageOwner, Integer> {
}
