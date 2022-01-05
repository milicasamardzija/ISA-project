package com.example.demo.repository.users;

import com.example.demo.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByEmail(String email);

}
