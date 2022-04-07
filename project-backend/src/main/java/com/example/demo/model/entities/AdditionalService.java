package com.example.demo.model.entities;

import com.example.demo.model.business.Action;
import com.example.demo.model.business.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @ManyToMany(mappedBy = "additionalServices")
    @JsonIgnore
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "additionalServices")
    @JsonIgnore
    private List<EntityClass> entities;

    @ManyToMany(mappedBy = "additionalServices")
    @JsonIgnore
    private List<Action> actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<EntityClass> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityClass> entities) {
        this.entities = entities;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
