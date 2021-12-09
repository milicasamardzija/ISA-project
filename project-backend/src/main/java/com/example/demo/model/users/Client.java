package com.example.demo.model.users;

import javax.persistence.*;

import com.example.demo.enums.LoyalityType;
import com.example.demo.model.business.Reservation;

import java.util.List;

@Entity
@Table(name = "client")
@DiscriminatorValue("CC")
public class Client extends User{
	
	@Column(name = "poents", nullable = true)
	private int poents;
	
	@Column(name = "loyalityType", nullable = true)
	private LoyalityType loyalityType;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations;

}
