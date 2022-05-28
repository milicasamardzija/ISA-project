package com.example.demo.dto.business;

import java.util.Date;

public class ReservationNewDTO {
    private Date dateStart;
    private String timeStart;
    private Date dateEnd;
    private double price;
    private int duration;
    private int entityId;

    public ReservationNewDTO() {}

    public ReservationNewDTO(Date dateStart, String timeStart, double price, int duration, int entityId) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.price = price;
        this.duration = duration;
        this.entityId = entityId;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
}
