package com.example.demo.model.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;

    @Column(name="dateStart", unique=false, nullable=false)
    private Date dateStart;

    @Column(name="dateEnd", unique=false, nullable=false)
    private Date dateEnd;

    @Column(name="price", unique=false, nullable=false)
    private double price;

    @Column(name="entity_type")
    private EntityType entityType;

    @Column(name="is_canceled",nullable=false)
    private Boolean isCanceled = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id")
    private EntityClass entity;

    public Reservation() {

    }

    public Reservation(int id, Date dateStart, Date dateEnd, double price, Client client) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }
}
