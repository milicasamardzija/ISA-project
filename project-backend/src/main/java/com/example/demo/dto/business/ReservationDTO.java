package com.example.demo.dto.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;

import java.util.Date;

public class ReservationDTO {

    private int id;

    private String dateStart;

    private String dateEnd;

    private double price;

    private EntityType entityType;

    private EntityClass entity;

    private Boolean isCanceled;

    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.dateStart = reservation.getDateStart().toString();
        this.dateEnd = reservation.getDateEnd().toString();
        this.price = reservation.getPrice();
        this.entityType = reservation.getEntityType();
        this.isCanceled = reservation.getCanceled();
        this.duration = reservation.getDuration();
    }
    public ReservationDTO() {

    }
}
