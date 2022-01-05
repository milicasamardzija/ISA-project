package com.example.demo.service.users;

import com.example.demo.model.users.Client;
import com.example.demo.repository.users.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client findById(String id){
        return this.clientRepository.findByEmail(id);
    }
}
