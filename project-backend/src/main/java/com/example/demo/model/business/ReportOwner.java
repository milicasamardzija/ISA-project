package com.example.demo.model.business;

import com.example.demo.enums.RestrictionType;
import com.example.demo.model.users.Client;
import com.example.demo.model.users.User;

import javax.persistence.*;

@Entity
@Table(name="report_owner")
public class ReportOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private  int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Client client;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User owner;

    @Column(name="comment",nullable=true)
    private String comment;

    @Column(name="revise",nullable=true)
    private Boolean revise;

    @Column(name="restriction_type",nullable=true)
    private RestrictionType restrictionType;

    @Column(name="approved", nullable = true)
    private Boolean approved;

    public ReportOwner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRevise() {
        return revise;
    }

    public void setRevise(Boolean revise) {
        this.revise = revise;
    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public ReportOwner(Client client, User owner, String comment, RestrictionType restrictionType, boolean approved) {
        this.client = client;
        this.owner = owner;
        this.comment = comment;
        this.restrictionType = restrictionType;
    this.approved = approved;
    }
}
