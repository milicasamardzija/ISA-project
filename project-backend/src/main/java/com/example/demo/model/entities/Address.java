package com.example.demo.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
//	@SequenceGenerator(name = "userSeqGen", sequenceName = "userSeqGen", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name = "country")
	private String country;
	@Column(name = "city")
	private String city;
	@Column(name = "street")
	private String street;
	@Column(name = "number")
	private String number;

	@Column(name = "longitude", nullable = true)
	private double longitude;
	@Column(name = "latitude", nullable = true)
	private double latitude;
	
	public Address() {}
	
	public Address( String country, String city, String street, String number, double longitude, double latitude) {
		super();

		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.longitude=longitude;
		this.latitude = latitude;
	}

	public Address(int id, String country, String city, String street, String number, double longitude, double latitude) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.longitude=longitude;
		this.latitude = latitude;
	}
//	public Address( String country, String city, String street, String number) {
//
//		this.country = country;
//		this.city = city;
//		this.street = street;
//		this.number = number;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
