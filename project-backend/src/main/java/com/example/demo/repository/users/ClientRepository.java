package com.example.demo.repository.users;

import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByEmail(String email);

    Client save(Client client);

    @Query("select e from EntityClass e left join fetch e.subscribedClients c where c.id = ?1")
    List<EntityClass> findSubscribedEnities(int idClient);

}
