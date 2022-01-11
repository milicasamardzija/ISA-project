package com.example.demo.dto.business;

import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.User;

public class ComplaintClientDTO {

    private String contentUser;

    private String contentEntity;

    private User user;

    private EntityClass entity;

    public ComplaintClientDTO() {

    }

    public String getContentUser() {
        return contentUser;
    }

    public void setContentUser(String contentUser) {
        this.contentUser = contentUser;
    }

    public String getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(String contentEntity) {
        this.contentEntity = contentEntity;
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
}
