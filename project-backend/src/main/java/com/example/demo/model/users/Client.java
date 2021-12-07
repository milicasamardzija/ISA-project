package com.example.demo.model.users;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.enums.LoyalityType;

@Entity
@Table(name = "client")
@DiscriminatorValue("CC")
public class Client extends User{
	
	@Column(name = "poents", nullable = false)
	private int poents;
	
	@Column(name = "loyalityType", nullable = false)
	private LoyalityType loyalityType;

}
