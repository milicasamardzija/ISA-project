package com.example.demo.model.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.entities.AdditionalService;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name="is_successful",nullable=false)
    private Boolean isSuccessful = false;

    @Column(name="action",nullable=true)
    private Boolean action = false;

    @Column(name="duration", nullable = true)
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("reservation")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    @JsonIgnoreProperties("entities")
    private EntityClass entity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="reservation_services", joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<AdditionalService> additionalServices;

    public Reservation() {

    }

    public Reservation(int id, Date dateStart, Date dateEnd, double price, Client client, int duration) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.client = client;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
