package com.example.demo.model.entities;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.example.demo.enums.CancelationType;

@Entity
@Table(name="adventure")
public class Adventure extends EntityClass{
	
	@Column(name="instructorBiografy", unique=false, nullable=true)
	private String instructorBiografy;
	
	@Column(name="maxNumberOfPeople", unique=false, nullable=true)
	private int maxNumberOfPeople;
	
	@Column(name="fishingEquipment", unique=false, nullable=true)
	private String fishingEquipment; //da li je dovoljno da ovo bude string, ili treba lista ili objekat??
	
	@Column(name="cancelationType", unique=false, nullable=true)
	private CancelationType cancelationType;
	
	public Adventure() {
		
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
