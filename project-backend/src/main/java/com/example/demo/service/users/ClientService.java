package com.example.demo.service.users;

import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.ClientRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Client findClientById(int id){
        return this.clientRepository.getById(id);
    }

    public List<EntityClass> findSubscribedEnities(int idClient){
        return this.clientRepository.findSubscribedEnities(idClient);
    }

    public void cancelSubsrciption(int idEntity, Client client){
        EntityClass entity = this.entityRepository.findById(idEntity);
        List<EntityClass> entities = this.findSubscribedEnities(client.getId());

        if (entities.size() > 0) {
            for (EntityClass e : entities
            ) {
                if (entities.size() > 0) {
                    if (e.getId() == idEntity) {
                        entities.remove(e);
                        client.setSubscribedEntities(entities);
                        this.clientRepository.save(client);
                        break;
                    }
                }
            }
        }

        List<Client> clients = this.clientRepository.findClientWithSubscribedEntities(idEntity);
        if (clients.size() > 0) {
            for (Client c : clients
            ) {
                if (clients.size() > 0) {
                    if (c.getId() == client.getId()) {
                        clients.remove(c);
                        entity.setSubscribedClients(clients);
                        this.entityRepository.save(entity);
                        break;
                    }
                }
            }
        }

    }

    public void addSubsrciptions(int idEntity, Client client){
        EntityClass entity = this.entityRepository.findById(idEntity);
        List<EntityClass> entities = this.findSubscribedEnities(client.getId());
        if (entities == null) {
            entities = new ArrayList<>();
        }
        entities.add(entity);
        client.setSubscribedEntities(entities);
        this.clientRepository.save(client);

        List<Client> clients = this.clientRepository.findClientWithSubscribedEntities(idEntity);
        if (clients == null){
            clients = new ArrayList<>();
        }
        clients.add(client);
        entity.setSubscribedClients(clients);
        this.entityRepository.save(entity);
    }

    public Client save(User user) {
        return this.clientRepository.save(new Client(user));
    }

    public Client save(Client client){
        return this.clientRepository.save(client);
    }

    @Scheduled(cron = "${greeting.cron}")
    public void deletePenals(){
        List<Client> clients = this.clientRepository.findAll();
        for (Client c : clients){
            c.setPenals(0);
            this.clientRepository.save(c);
        }
    }


}
