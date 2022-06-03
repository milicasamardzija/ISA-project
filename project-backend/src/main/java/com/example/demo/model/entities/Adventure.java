package com.example.demo.model.entities;

import javax.persistence.*;

import com.example.demo.dto.enums.CancelationType;
import com.example.demo.model.business.ReservationServices;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="adventure")
public class Adventure extends EntityClass{
	@Column(name="nameOfAdventure", unique=false, nullable=true)
	private String nameOfAdventure;

	@Column(name="adrress", unique=false, nullable=true)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE ) //PRILIKOM REGISTRACIJE NIJE CUVAO USERA, PA SAM PROMENILA CASCADETYPE
	private Address address;

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
		this.address=adrress;
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

	public String getNameOfAdventure() {
		return nameOfAdventure;
	}

	public void setNameOfAdventure(String nameOfAdventure) {
		this.nameOfAdventure = nameOfAdventure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getRules() {
		return rules;
	}

	@Override
	public void setRules(String rules) {
		this.rules = rules;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
}
