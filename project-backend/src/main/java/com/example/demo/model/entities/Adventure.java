package com.example.demo.model.entities;

import javax.persistence.*;

import com.example.demo.enums.CancelationType;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="adventure")
public class Adventure extends EntityClass{
	@Column(name="nameOfAdventure", unique=false, nullable=true)
	private String nameOfAdventure;

	//U ENTITY CLASS VEC IMA INFORMACIJA O ADRESI
	//@Column(name="adrress", unique=false, nullable=true)
	//@OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
	//private Address address;

	@Column(name="description", unique=false, nullable=true)
	private String description;

	@Column(name="instructorBiografy", unique=false, nullable=true)
	private String instructorBiografy;

	@Column(name="rules", unique=false, nullable=true)
	private String rules;

	@Column(name="maxNumberOfPeople", unique=false, nullable=true)
	private int maxNumberOfPeople;
	
	@Column(name="fishingEquipment", unique=false, nullable=true)
	private String fishingEquipment; //da li je dovoljno da ovo bude string, ili treba lista ili objekat??
	
	@Column(name="cancelationType", unique=false, nullable=true)
	private CancelationType cancelationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id")
	@JsonIgnoreProperties("instructor")
	public Instructor instructor;

	public Adventure() {
		super();
	}

	public Adventure(String nameOfAdventure, Address adrress, String description, String instructorBiografy, String rules, int maxNumberOfPeople, String fishingEquipment, CancelationType cancelationType) {
		this.nameOfAdventure = nameOfAdventure;
		this.setAddress(adrress);
		this.description = description;
		this.instructorBiografy = instructorBiografy;
		this.rules = rules;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.fishingEquipment = fishingEquipment;
		this.cancelationType = cancelationType;
	}

	public Adventure(String instructorBiografy, int maxNumberOfPeople, String fishingEquipment,
					 CancelationType cancelationType) {
		super();
		this.instructorBiografy = instructorBiografy;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.fishingEquipment = fishingEquipment;
		this.cancelationType = cancelationType;
	}

	public String getInstructorBiografy() {
		return instructorBiografy;
	}

	public void setInstructorBiografy(String instructorBiografy) {
		this.instructorBiografy = instructorBiografy;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public String getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(String fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public CancelationType getCancelationType() {
		return cancelationType;
	}

	public void setCancelationType(CancelationType cancelationType) {
		this.cancelationType = cancelationType;
	}
		
}
