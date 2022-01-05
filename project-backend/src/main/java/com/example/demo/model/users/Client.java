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

	@Column(name = "penals", nullable = true)
	private int penals;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations;

	public int getPoents() {
		return poents;
	}

	public void setPoents(int poents) {
		this.poents = poents;
	}

	public LoyalityType getLoyalityType() {
		return loyalityType;
	}

	public void setLoyalityType(LoyalityType loyalityType) {
		this.loyalityType = loyalityType;
	}

	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Client(){
		super();
	}
}
