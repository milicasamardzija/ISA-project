package com.example.demo.model.business;

import com.example.demo.enums.ComplaintType;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

import javax.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    @Column
    private ComplaintType complaintType;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY )
    private EntityClass entity;

    @OneToOne(fetch = FetchType.LAZY)
    private User userWhoSendsComplaint;

    public Complaint() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ComplaintType getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(ComplaintType complaintType) {
        this.complaintType = complaintType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EntityClass getEntity() {
        return entity;
    }

    public void setEntity(EntityClass entity) {
        this.entity = entity;
    }

    public User getUserWhoSendsComplaint() {
        return userWhoSendsComplaint;
    }

    public void setUserWhoSendsComplaint(User userWhoSendsComplaint) {
        this.userWhoSendsComplaint = userWhoSendsComplaint;
    }
}
