package com.example.demo.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "boatOwner")
@DiscriminatorValue("BO")
public class BoatOwner extends User {
	
	public BoatOwner() {
		
	}
}
