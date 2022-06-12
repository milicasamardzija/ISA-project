package com.example.demo.dto.business;

import com.example.demo.enums.RestrictionType;

public class ReportForOwnerDTO {

    public  int idClient;
    public int idOwner;
    public String comment;
    public RestrictionType restrictionType;

    public ReportForOwnerDTO() {
    }

    public ReportForOwnerDTO(int idClient, int idOwner, String comment, RestrictionType restrictionType) {
        this.idClient = idClient;
        this.idOwner = idOwner;
        this.comment = comment;
        this.restrictionType = restrictionType;
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

    public RestrictionType getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(RestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }
}
