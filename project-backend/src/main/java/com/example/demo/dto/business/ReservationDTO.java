package com.example.demo.dto.business;

import com.example.demo.enums.EntityType;
import com.example.demo.model.business.Reservation;
import java.util.Date;

public class ReservationDTO {

    private int id;

    private Date dateStart;

    private Date dateEnd;

    private double price;

    private EntityType entityType;

    private Boolean isCanceled = false;

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

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.dateStart = reservation.getDateStart();
        this.dateEnd = reservation.getDateEnd();
        this.price = reservation.getPrice();
        this.entityType = reservation.getEntityType();
        this.isCanceled = reservation.getCanceled();
    }
}
