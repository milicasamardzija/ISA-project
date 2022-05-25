package com.example.demo.dto.business;

import com.example.demo.enums.ComplaintType;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

import javax.persistence.*;

public class ComplaintClientDTO {

    private int id;

    public String getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(String contentEntity) {
        this.contentEntity = contentEntity;
    }

    public String getContentUser() {
        return contentUser;
    }

    public void setContentUser(String contentUser) {
        this.contentUser = contentUser;
    }

    private String contentEntity;
    private String contentUser;
    private String complaintType;
    private User user;
    private EntityClass entity;
    private User userWhoSendsComplaint;

    public ComplaintClientDTO() {

    }
    public ComplaintClientDTO(Complaint complaint) {
        this.id = complaint.getId();
        this.contentEntity = complaint.getContentEntity();
        this.contentUser = complaint.getContentUser();
        this.complaintType = complaint.getComplaintType().toString();
        this.user = complaint.getUser();
        this.userWhoSendsComplaint = complaint.getUserWhoSendsComplaint();
    }
    public int getId() {
        return id;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public User getUser() {
        return user;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public User getUserWhoSendsComplaint() {
        return userWhoSendsComplaint;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public void setUserWhoSendsComplaint(User userWhoSendsComplaint) {
        this.userWhoSendsComplaint = userWhoSendsComplaint;
    }
}
