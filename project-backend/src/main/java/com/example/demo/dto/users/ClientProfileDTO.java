package com.example.demo.dto.users;

import com.example.demo.model.users.Client;

public class ClientProfileDTO {

    public String name;
    public int id;
    public String surname;
    public String email;

    public ClientProfileDTO(String name, int id, String surname, String email) {
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.email = email;
    }

    public ClientProfileDTO(){}

    public  ClientProfileDTO(Client c){
        this.id = c.getId();
        this.email = c.getEmail();
        this.surname = c.getSurname();
        this.name = c.getSurname();
    }
}
