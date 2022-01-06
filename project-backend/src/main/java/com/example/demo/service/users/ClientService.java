package com.example.demo.service.users;

import com.example.demo.model.entities.Cottage;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.repository.users.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client findById(String id){
        return this.clientRepository.findByEmail(id);
    }

    public List<EntityClass> findSubscribedEnities(int idClient){
        return this.clientRepository.findSubscribedEnities(idClient);
    }

    public void cancelSubsrciption(int idEntity, int idUser){

    }
}
