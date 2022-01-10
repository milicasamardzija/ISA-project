package com.example.demo.model.users;

import com.example.demo.dto.users.UserRequest;
import com.example.demo.model.entities.Address;
import com.example.demo.model.entities.Adventure;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructor")
@Proxy(lazy = false)
@DiscriminatorValue("II")
public class Instructor extends User{

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private List<Adventure> adventuresList;

    public List<Adventure> getAdventures() {
        return adventuresList;
    }

    public void setAdventures(List<Adventure> adventuresList) {
        this.adventuresList = adventuresList;
    }

    public Instructor() {}

    public Instructor(String name, String surname, String email, String password, String telephone, Address address, List<Adventure> adventuresList) {
        super(name, surname, email, password, telephone, address);
        this.adventuresList = adventuresList;
    }

    public Instructor(UserRequest user) {
        super(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), user.getRole());
    }

    public Instructor(User user) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getTelephone(), user.getAddress(), true, user.getRole(), user.getLastPasswordResetDate());
    }
}
