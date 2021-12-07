package com.example.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "boatOwner")
@DiscriminatorValue("BO")
public class BoatOwner extends User {

}
