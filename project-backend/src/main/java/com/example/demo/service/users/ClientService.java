package com.example.demo.service.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private EntityRepository entityRepository;

    public ClientService (ClientRepository clientRepository, EntityRepository entityRepository){
        this.clientRepository = clientRepository;
        this.entityRepository = entityRepository;
    }

    public Client findById(String id){
        return this.clientRepository.findByEmail(id);
    }

    public List<EntityClass> findSubscribedEnities(int idClient){
        return this.clientRepository.findSubscribedEnities(idClient);
    }

    public void cancelSubsrciption(int idEntity, String idClient){

    }

    public Client save(User user) {
        return  this.clientRepository.save(new Client(user));
    }
}
