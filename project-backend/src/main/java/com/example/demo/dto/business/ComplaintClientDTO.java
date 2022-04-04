package com.example.demo.dto.business;

import com.example.demo.enums.ComplaintType;
import com.example.demo.model.business.Complaint;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

import javax.persistence.*;

public class ComplaintClientDTO {

    private int id;
    private String content;
    private String complaintType;
    private User user;
    private EntityClass entity;
    private User userWhoSendsComplaint;

    public ComplaintClientDTO() {

    }
    public ComplaintClientDTO(Complaint complaint) {
        this.id = complaint.getId();
        this.content = complaint.getContent();
        this.complaintType = complaint.getComplaintType().toString();
        this.user = complaint.getUser();
        this.userWhoSendsComplaint = complaint.getUserWhoSendsComplaint();
    }
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
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

    public void setContent(String content) {
        this.content = content;
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
