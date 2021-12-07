package com.example.demo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cottage")
public class Cottage extends EntityClass {

	@Column(name="roomsNumber", unique=false, nullable=true)
	private int roomsNumber;
	
	@Column(name="bedsByRoom", unique=false, nullable=true)
	private int bedsByRoom;
	
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
