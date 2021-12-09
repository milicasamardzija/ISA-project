package com.example.demo.model.entities;

import com.example.demo.model.users.CottageOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="cottage")
public class Cottage extends EntityClass {

	@Column(name="roomsNumber", unique=false, nullable=true)
	private int roomsNumber;
	
	@Column(name="bedsByRoom", unique=false, nullable=true)
	private int bedsByRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id") //ovako ce se zvati id polje u bazi
	@JsonIgnoreProperties("cottageOwner")
	public CottageOwner cottageOwner;

	public Cottage() {
		super();
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public int getBedsByRoom() {
		return bedsByRoom;
	}

	public void setBedsByRoom(int bedsByRoom) {
		this.bedsByRoom = bedsByRoom;
	}
	
	
}
