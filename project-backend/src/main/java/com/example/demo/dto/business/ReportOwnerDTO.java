package com.example.demo.dto.business;

import com.example.demo.enums.RestrictionType;

public class ReportOwnerDTO {
    public  int idClient;
    public int idOwner;
    public String comment;
    public RestrictionType restrictionType;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RestrictionType getRestictionType() {
        return restrictionType;
    }

    public void setRestictionType(RestrictionType restictionType) {
        this.restrictionType = restictionType;
    }

    public ReportOwnerDTO(int idClient, int idOwner, String comment, RestrictionType restictionType) {
        this.idClient = idClient;
        this.idOwner = idOwner;
        this.comment = comment;
        this.restrictionType = restictionType;
    }

    public ReportOwnerDTO() {
    }
}
